package com.demo.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "otp_validator")
@JsonPropertyOrder({ "mobile_number", "otp", "expire_time", "created_time" })
public class OTPValidator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private int id;

	@JsonProperty("mobile_number")
	private String mobileNumber;

	@JsonProperty("otp")
	private String otp;

	@JsonProperty("expire_time")
	private Date expireTime;
	
	@JsonProperty("created_time")
	private Date createdTime;

	@Transient
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}

	@JsonProperty("mobile_number")
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@JsonProperty("otp")
	public String getOtp() {
		return otp;
	}

	@JsonProperty("otp")
	public void setOtp(String otp) {
		this.otp = otp;
	}

	@JsonProperty("expire_time")
	public Date getExpireTime() {
		return expireTime;
	}

	@JsonProperty("expire_time")
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	@JsonProperty("created_time")
	public Date getCreatedTime() {
		return createdTime;
	}

	@JsonProperty("created_time")
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}