package com.ha.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ha.java.entity.BranchAccount;
import com.ha.java.entity.UserAccount;

public interface BranchAccountRepository extends JpaRepository<BranchAccount, Long>{

	Optional<BranchAccount> findByBranchId(Long id);
	
	Optional<BranchAccount> findByAccountNumber(String accountNumber);
}
