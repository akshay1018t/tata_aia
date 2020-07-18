package com.demo.transactional;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.dto.CustomerPolicyInformation;

@Repository
public interface CustomerTransactional extends JpaRepository<CustomerPolicyInformation, Long> {

	@Query(value = "SELECT * FROM customer_policy_information customer WHERE customer.policy_number = ?1", nativeQuery = true)
	List<CustomerPolicyInformation> getCustomerByPolicyNumber(Long policyNumber);

	@Query(value = "SELECT * FROM customer_policy_information c WHERE c.email_address=?1 AND c.dob=?2", nativeQuery = true)
	List<CustomerPolicyInformation> getCustomerByEmail(String email, String dob);

	@Query(value = "SELECT * FROM customer_policy_information c WHERE c.contact_number_last_updated=?1 AND c.dob=?2", nativeQuery = true)
	List<CustomerPolicyInformation> getCustomerByMobileAndDob(String mobileNumber, String dob);

	@Query(value = "SELECT * FROM customer_policy_information c WHERE c.contact_number_last_updated=?1", nativeQuery = true)
	List<CustomerPolicyInformation> getCustomerByMobile(String mobileNumber);

}
