package com.ha.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ha.java.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{

	Optional<UserAccount> findByUserId(Long id);
	
	Optional<UserAccount> findByAccountNumber(String accountNumber);
}
