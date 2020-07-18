package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AuthenticationResponseDTO;
import com.demo.dto.CustomerPolicyInformation;
import com.demo.dto.OptinResponse;
import com.demo.dto.ValidationResponseDTO;
import com.demo.service.AuthenticationService;
import com.demo.service.CustomerService;
import com.demo.service.util.Constants;

@RestController
public class CustomerController {

	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/authenticate")
	public AuthenticationResponseDTO getAuthToken(@RequestBody Map<String, Object> inputRequest) {
		
		String mobileNumber = (String) inputRequest.get("contact_number_last_updated");
		String otp = (String) inputRequest.get("otp");
		customerService.saveCustomerInfo(mobileNumber, otp);
		
		AuthenticationResponseDTO response = authService.getAuthTokens(inputRequest);
		return response;
		
	}
	
	@PostMapping("/addCustomer")
	public void addCustomer(@RequestBody CustomerPolicyInformation customer) {
		customerService.saveCustomer(customer);
	}
	
	@PostMapping("/getCustomer")
	public ValidationResponseDTO getCustomer(@RequestBody HashMap<String, Object> requestMap) {
		CustomerPolicyInformation returnCustomer = new CustomerPolicyInformation();
		ValidationResponseDTO validation = new ValidationResponseDTO();
		String policyNumber = (String) requestMap.get("policy_number");
		returnCustomer = customerService.getCustomerByMobileNumber(policyNumber);

		// JWT verification 
		validation.setData(returnCustomer);
		validation.setStatus(Constants.GET);
		
		return validation;
	}

	@PostMapping("/emailValidation")
	public ValidationResponseDTO emailValidation(@RequestBody HashMap<String, Object> requestMap) {
		String emailAddress = (String) requestMap.get("email_address");
		String dob = (String) requestMap.get("dob");
		
		ValidationResponseDTO validation = new ValidationResponseDTO();
		boolean isValid = customerService.checkCustomerByEmail(emailAddress, dob);

		if(isValid) {
			validation.setData(emailAddress);
			validation.setStatus(Constants.VALIDATION);
		} 
		
		return validation;
	}
	
	@PostMapping("/mobileValidation")
	public ValidationResponseDTO mobileValidation(@RequestBody HashMap<String, Object> requestMap) {
		String mobileNumber = (String) requestMap.get("contact_number_last_updated");
		String dob = (String) requestMap.get("dob");
		
		ValidationResponseDTO validation = new ValidationResponseDTO();
		boolean isValid = customerService.checkCustomerByMobile(mobileNumber, dob);

		if(isValid) {
			validation.setData(mobileNumber);
			validation.setStatus(Constants.VALIDATION);
		} 
		
		return validation;
	}

	@PostMapping("/mobileUpdation")
	public ValidationResponseDTO mobileUpdation(@RequestBody HashMap<String, Object> requestMap) {
		String mobileNumber = (String) requestMap.get("mobile_number");
		String policyNumber = (String) requestMap.get("policy_number");
		
		customerService.updateCustomerMobile(mobileNumber, policyNumber);
		
		ValidationResponseDTO validation = new ValidationResponseDTO();
		
		validation.setData("SR10001");
		validation.setStatus(Constants.REQUEST);
		validation.setMessage("Service Request for Mobile Number Updation Generated");
		
		return validation;
	}
	
	@PostMapping("/emailUpdation")
	public ValidationResponseDTO emailUpdation(@RequestBody HashMap<String, Object> requestMap) {
		String emailAddress = (String) requestMap.get("email_address");
		String policyNumber = (String) requestMap.get("policy_number");
		
		customerService.updateEmailAddress(emailAddress, policyNumber);
		
		ValidationResponseDTO validation = new ValidationResponseDTO();
		
		validation.setData("SR10002");
		validation.setStatus(Constants.REQUEST);
		validation.setMessage("Service Request for Email Address Updation Generated");
		
		return validation;
	}
	
	@PostMapping("/panUpdation")
	public ValidationResponseDTO panUpdation(@RequestBody HashMap<String, Object> requestMap) {
		String panNumber = (String) requestMap.get("customer_pan_no");
		String policyNumber = (String) requestMap.get("policy_number");
		
		customerService.updatePanNumber(panNumber, policyNumber);
		
		ValidationResponseDTO validation = new ValidationResponseDTO();
		
		validation.setData("SR10003");
		validation.setStatus(Constants.REQUEST);
		validation.setMessage("Service Request for PAN Address Updation Generated");
		
		return validation;
	}
	
	@PostMapping("/optinRequest")
	public OptinResponse optinRequest(@RequestBody HashMap<String, Object> requestMap) {
		String mobileNumber = (String) requestMap.get("mobile_number");
		
		OptinResponse response = customerService.optinRequest(mobileNumber);

		return response;
	}
	
	@PostMapping("/optinValidation")
	public OptinResponse optinValidation(@RequestBody HashMap<String, Object> requestMap) {
		String mobileNumber = (String) requestMap.get("mobile_number");
		String otp = (String) requestMap.get("otp");
		
		OptinResponse response = customerService.optinValidator(mobileNumber, otp);
		
		return response;
	}
		
}
