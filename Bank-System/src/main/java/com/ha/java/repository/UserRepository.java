package com.ha.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ha.java.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByBranchId(Long branchId);
	
}
