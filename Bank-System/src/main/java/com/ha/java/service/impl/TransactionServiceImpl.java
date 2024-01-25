package com.ha.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ha.java.entity.Transaction;
import com.ha.java.repository.TransactionRepository;
import com.ha.java.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction postTransaction(Transaction transaction) {

		return transactionRepository.save(transaction);
	}
	
	
	
	
}
