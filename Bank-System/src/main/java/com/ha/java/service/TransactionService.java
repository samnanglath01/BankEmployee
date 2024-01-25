package com.ha.java.service;

import com.ha.java.entity.Transaction;

public interface TransactionService {

	//save transaction when customer or branch does transaction
	Transaction postTransaction(Transaction transaction);
	
	//get all transaction
	
}
