package com.ha.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ha.java.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
