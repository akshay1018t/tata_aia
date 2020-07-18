package com.demo.transactional;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.dto.OTPValidator;


@Repository
public interface OptinOtpValidatorTransaction extends JpaRepository<OTPValidator, Long>{

	 @Query(value = "SELECT * FROM otp_validator o WHERE o.mobile_number=?1 AND o.otp=?2 AND o.expire_time>?3", nativeQuery= true) 
	  List<OTPValidator> getmobileInformation(String mobileNumber, String otp, Date currentTime);
}
