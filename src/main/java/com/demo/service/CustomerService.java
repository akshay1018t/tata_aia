package com.demo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.dto.CustomerPolicyInformation;
import com.demo.dto.OTPValidator;
import com.demo.dto.OptinInformationDTO;
import com.demo.dto.OptinResponse;
import com.demo.helper.CustomerServiceHelper;
import com.demo.service.util.Constants;
import com.demo.transactional.CustomerTransactional;
import com.demo.transactional.OptinInformationTransactional;
import com.demo.transactional.OptinOtpValidatorTransaction;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class CustomerService {

	@Value("${account.sid}")
	private String accountSid;
	
	@Value("${auth.token}")
	private String authToken;
	
	@Autowired
	CustomerServiceHelper customerServiceHelper;
	
	@Autowired
	CustomerTransactional customerTransactional;
	
	@Autowired
	OptinInformationTransactional optinInformationTransactional;
	
	@Autowired
	OptinOtpValidatorTransaction optinOtpValidatorTransaction;
	
	
	public void saveCustomer(CustomerPolicyInformation customer) {
		
		customerTransactional.save(customer);
	}
	
	
	public void saveCustomerInfo(String mobileNumber, String otp) {
		
		Instant now = Instant.now();
		Date currentTime = Date.from(now);
		Date expiryTime = Date.from(now.plus(3, ChronoUnit.MINUTES));
		OTPValidator customerInfo = new OTPValidator();
		customerInfo.setMobileNumber(mobileNumber);
		customerInfo.setOtp(otp);
		customerInfo.setCreatedTime(currentTime);
		customerInfo.setExpireTime(expiryTime);
		
		optinOtpValidatorTransaction.save(customerInfo);
		
	} 

	
	public CustomerPolicyInformation getCustomerByMobileNumber(String policyNumber) {
		
		CustomerPolicyInformation customer = new CustomerPolicyInformation();
		
		customer = customerTransactional.getCustomerByPolicyNumber(Long.valueOf(policyNumber)).get(0);
	
		return customer;
	}
	
	
	public boolean checkCustomerByEmail(String emailAddress, String dob) {
		
		
		List<CustomerPolicyInformation> custList = customerTransactional.getCustomerByEmail(emailAddress, dob);
		
		if(custList.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	
	public boolean checkCustomerByMobile(String mobileNumber, String dob) {
		List<CustomerPolicyInformation> custList = customerTransactional.getCustomerByMobileAndDob(mobileNumber, dob);
		
		if(custList.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	
	public void updateCustomerMobile(String mobileNumber, String policyNumber) {

		CustomerPolicyInformation customer = new CustomerPolicyInformation();
		
		customer = customerTransactional.getCustomerByPolicyNumber(Long.valueOf(policyNumber)).get(0);
		
		customer.setContactNumberLastUpdated(mobileNumber);
		
		customerTransactional.save(customer);
		
	}

	
	public void updateEmailAddress(String emailAddress, String policyNumber) {

		CustomerPolicyInformation customer = new CustomerPolicyInformation();
		
		customer = customerTransactional.getCustomerByPolicyNumber(Long.valueOf(policyNumber)).get(0);
		
		customer.setEmailAddress(emailAddress);
		
		customer.setEmailAddressLastUpdated(emailAddress);
		
		customerTransactional.save(customer);
		
	}

	
	public void updatePanNumber(String panNumber, String policyNumber) {

		CustomerPolicyInformation customer = new CustomerPolicyInformation();
		
		customer = customerTransactional.getCustomerByPolicyNumber(Long.valueOf(policyNumber)).get(0);
		
		customer.setCustomerPanNo(panNumber);
		
		customerTransactional.save(customer);
		
	}

	@SuppressWarnings("unused")
	public OptinResponse optinRequest(String mobileNumber) {
		OptinResponse response = new OptinResponse();
		
		if(optinInformationTransactional.getOptinInformation(mobileNumber).size()>0) {

			OptinInformationDTO optinInformation = optinInformationTransactional.getOptinInformation(mobileNumber).get(0);
			
			response.setMessage("Thank you for Optin Request Completed");
			response.setStatus(Constants.OLD_USER);
		}
		else {
			// Initialize TWILIO
			Twilio.init(accountSid, authToken);
			
			String otp = customerServiceHelper.getOpt();
			
			customerServiceHelper.saveOtp(otp, mobileNumber);
			
			Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("whatsapp:+91" + mobileNumber),
	                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
	               "Your OTP is " + otp)
	            .create();
			response.setMessage("Optin OTP Sent on the Mobile Number");
			response.setStatus(Constants.OTP_SENT);
		}
		
		return response;
	}

	
	public OptinResponse optinValidator(String mobileNumber, String otp) {

		OptinResponse response = new OptinResponse();
		OptinInformationDTO custData = new OptinInformationDTO();
		Instant now = Instant.now();
		Date currentTime = Date.from(now);
		
		OTPValidator custInfo = optinOtpValidatorTransaction.getmobileInformation(mobileNumber, otp, currentTime).get(0);
		
		if(optinOtpValidatorTransaction.getmobileInformation(mobileNumber, otp, currentTime).size()>0) {
			 
			CustomerPolicyInformation customer = customerTransactional.getCustomerByMobile(mobileNumber).get(0);
			
			custData.setPolicyNumber(customer.getPolicyNumber()); 
			custData.setMobileNumber(mobileNumber);
			custData.setOptinDate(custInfo.getCreatedTime());
			optinInformationTransactional.save(custData);
			
			response.setMessage("Thank you for Optin Request Completed");
			response.setStatus(Constants.NEW_USER);
		} else {
			response.setMessage("Invalid Request");
			response.setStatus(Constants.NOT_FOUND);
		}
		
		return response;
	}
	
}
