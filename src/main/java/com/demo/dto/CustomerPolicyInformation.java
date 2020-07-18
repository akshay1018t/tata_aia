package com.demo.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "policy_number", "customer_id", "insured_name", "dob", "email_address", "premium_mode",
		"policy_status", "customer_pan_no", "contact_number_last_updated", "email_address_last_updated",
		"bank_account_number", "whatsapp_opt_in_status", "product_name", "product_id", "reinvest_applicable",
		"outstanding_payout", "unclaimed_amount", "neft_registered", "last_premium_paid" })
public class CustomerPolicyInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6077230653823575975L;
	@Id
	@Column(name="policy_number")
	@JsonProperty("policy_number")
	private Long policyNumber;
	
	@Column(name="customer_id")
	@JsonProperty("customer_id")
	private Integer customerId;
	
	@Column(name="insured_name")
	@JsonProperty("insured_name")
	private String insuredName;
	
	@Column(name="dob")
	@JsonProperty("dob")
	private String dob;
	
	@Column(name="email_address")
	@JsonProperty("email_address")
	private String emailAddress;
	
	@Column(name="premium_mode")
	@JsonProperty("premium_mode")
	private String premiumMode;
	
	@Column(name="policy_status")
	@JsonProperty("policy_status")
	private String policyStatus;
	
	@Column(name="customer_pan_no")
	@JsonProperty("customer_pan_no")
	private String customerPanNo;
	
	@Column(name="contact_number_last_updated")
	@JsonProperty("contact_number_last_updated")
	private String contactNumberLastUpdated;
	
	@Column(name="email_address_last_updated")
	@JsonProperty("email_address_last_updated")
	private String emailAddressLastUpdated;
	
	@Column(name="bank_account_number")
	@JsonProperty("bank_account_number")
	private String bankAccountNumber;
	
	@Column(name="whatsapp_opt_in_status")
	@JsonProperty("whatsapp_opt_in_status")
	private Boolean whatsappOptInStatus;
	
	@Column(name="product_name")
	@JsonProperty("product_name")
	private String productName;
	
	@Column(name="product_id")
	@JsonProperty("product_id")
	private Integer productId;
	
	@Column(name="reinvest_applicable")
	@JsonProperty("reinvest_applicable")
	private Boolean reinvestApplicable;
	
	@Column(name="outstanding_payout")
	@JsonProperty("outstanding_payout")
	private Float outstandingPayout;
	
	@Column(name="unclaimed_amount")
	@JsonProperty("unclaimed_amount")
	private Float unclaimedAmount;
	
	@Column(name="neft_registered")
	@JsonProperty("neft_registered")
	private Boolean neftRegistered;
	
	@Column(name="last_premium_paid")
	@JsonProperty("last_premium_paid")
	private Boolean lastPremiumPaid;
	
	@Transient
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("policy_number")
	public Long getPolicyNumber() {
		return policyNumber;
	}

	@JsonProperty("policy_number")
	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}

	@JsonProperty("customer_id")
	public Integer getCustomerId() {
		return customerId;
	}

	@JsonProperty("customer_id")
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@JsonProperty("insured_name")
	public String getInsuredName() {
		return insuredName;
	}

	@JsonProperty("insured_name")
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	@JsonProperty("dob")
	public String getDob() {
		return dob;
	}

	@JsonProperty("dob")
	public void setDob(String dob) {
		this.dob = dob;
	}

	@JsonProperty("email_address")
	public String getEmailAddress() {
		return emailAddress;
	}

	@JsonProperty("email_address")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@JsonProperty("premium_mode")
	public String getPremiumMode() {
		return premiumMode;
	}

	@JsonProperty("premium_mode")
	public void setPremiumMode(String premiumMode) {
		this.premiumMode = premiumMode;
	}

	@JsonProperty("policy_status")
	public String getPolicyStatus() {
		return policyStatus;
	}

	@JsonProperty("policy_status")
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	@JsonProperty("customer_pan_no")
	public String getCustomerPanNo() {
		return customerPanNo;
	}

	@JsonProperty("customer_pan_no")
	public void setCustomerPanNo(String customerPanNo) {
		this.customerPanNo = customerPanNo;
	}

	@JsonProperty("contact_number_last_updated")
	public String getContactNumberLastUpdated() {
		return contactNumberLastUpdated;
	}

	@JsonProperty("contact_number_last_updated")
	public void setContactNumberLastUpdated(String contactNumberLastUpdated) {
		this.contactNumberLastUpdated = contactNumberLastUpdated;
	}

	@JsonProperty("email_address_last_updated")
	public String getEmailAddressLastUpdated() {
		return emailAddressLastUpdated;
	}

	@JsonProperty("email_address_last_updated")
	public void setEmailAddressLastUpdated(String emailAddressLastUpdated) {
		this.emailAddressLastUpdated = emailAddressLastUpdated;
	}

	@JsonProperty("bank_account_number")
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	@JsonProperty("bank_account_number")
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	@JsonProperty("whatsapp_opt_in_status")
	public Boolean getWhatsappOptInStatus() {
		return whatsappOptInStatus;
	}

	@JsonProperty("whatsapp_opt_in_status")
	public void setWhatsappOptInStatus(Boolean whatsappOptInStatus) {
		this.whatsappOptInStatus = whatsappOptInStatus;
	}

	@JsonProperty("product_name")
	public String getProductName() {
		return productName;
	}

	@JsonProperty("product_name")
	public void setProductName(String productName) {
		this.productName = productName;
	}

	@JsonProperty("product_id")
	public Integer getProductId() {
		return productId;
	}

	@JsonProperty("product_id")
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@JsonProperty("reinvest_applicable")
	public Boolean getReinvestApplicable() {
		return reinvestApplicable;
	}

	@JsonProperty("reinvest_applicable")
	public void setReinvestApplicable(Boolean reinvestApplicable) {
		this.reinvestApplicable = reinvestApplicable;
	}

	@JsonProperty("outstanding_payout")
	public Float getOutstandingPayout() {
		return outstandingPayout;
	}

	@JsonProperty("outstanding_payout")
	public void setOutstandingPayout(Float outstandingPayout) {
		this.outstandingPayout = outstandingPayout;
	}

	@JsonProperty("unclaimed_amount")
	public Float getUnclaimedAmount() {
		return unclaimedAmount;
	}

	@JsonProperty("unclaimed_amount")
	public void setUnclaimedAmount(Float unclaimedAmount) {
		this.unclaimedAmount = unclaimedAmount;
	}

	@JsonProperty("neft_registered")
	public Boolean getNeftRegistered() {
		return neftRegistered;
	}

	@JsonProperty("neft_registered")
	public void setNeftRegistered(Boolean neftRegistered) {
		this.neftRegistered = neftRegistered;
	}

	@JsonProperty("last_premium_paid")
	public Boolean getLastPremiumPaid() {
		return lastPremiumPaid;
	}

	@JsonProperty("last_premium_paid")
	public void setLastPremiumPaid(Boolean lastPremiumPaid) {
		this.lastPremiumPaid = lastPremiumPaid;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	} 
}