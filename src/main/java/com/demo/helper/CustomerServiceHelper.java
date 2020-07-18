package com.demo.helper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.OTPValidator;
import com.demo.transactional.OptinOtpValidatorTransaction;

@Service
public class CustomerServiceHelper {
	
	@Autowired
	OptinOtpValidatorTransaction otpTransactional;

	/**
	 * 
	 * Taking the static length of otp as 6
	 * @return
	 */
	public String getOpt() {
		String otp = null;

		Random otpRandom = new Random();
		char[] otpCharArray = new char[6];
		for (int i = 0; i < 6; i++) {
			otpCharArray[i] = (char) (otpRandom.nextInt(10) + 48);
		}
		otp = String.valueOf(otpCharArray);
		return otp;
	}

	public void saveOtp(String otp, String mobileNumber) {
		Instant now = Instant.now();
		Date createdDate = Date.from(now);
		Date expiryDate = Date.from(now.plus(3, ChronoUnit.MINUTES));
		OTPValidator validator = new OTPValidator();
		validator.setMobileNumber(mobileNumber);
		validator.setOtp(otp);
		validator.setCreatedTime(createdDate);
		validator.setExpireTime(expiryDate);
		otpTransactional.save(validator);
	}
	
}
