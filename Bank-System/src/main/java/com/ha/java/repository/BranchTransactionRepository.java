package com.ha.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ha.java.entity.BranchTransaction;

public interface BranchTransactionRepository extends JpaRepository<BranchTransaction, Long>{

}
