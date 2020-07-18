package com.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "optin_info")
@JsonPropertyOrder({ "optin_id", "mobile_number", "policy_number", "optin_date" })
public class OptinInformationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7044353717964004021L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("optin_id")
	private Integer optinId;

	@JsonProperty("mobile_number")
	private String mobileNumber;

	@Column(unique = true)
	@JsonProperty("policy_number")
	private Long policyNumber;

	@JsonProperty("optin_date")
	private Date optinDate;

	@Transient
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();


	public Integer getOptinId() {
		return optinId;
	}

	public void setOptinId(Integer optinId) {
		this.optinId = optinId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Date getOptinDate() {
		return optinDate;
	}

	public void setOptinDate(Date optinDate) {
		this.optinDate = optinDate;
	}

}
