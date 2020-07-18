package com.demo.transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.dto.OptinInformationDTO;

public interface OptinInformationTransactional extends JpaRepository<OptinInformationDTO, Long> {

	@Query(nativeQuery = true, value = "SELECT * from optin_info x WHERE x.mobile_number=?1")
	List<OptinInformationDTO> getOptinInformation(String mobileNumber);
}
