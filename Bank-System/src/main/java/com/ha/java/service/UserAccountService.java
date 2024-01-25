package com.ha.java.service;

import com.ha.java.dto.AccountDto;

public interface UserAccountService {

	// create UserAccount
	AccountDto postAccount(AccountDto accountDto);
	
	//get all UserAccount

	// get UserAccount by User id
	AccountDto getAccountByUserId(Long id);

	// update pin of UserAccount by user id
	AccountDto putPinAccountByUserId(Long id, String newPin);

	// send money from user id to {accountNumber} user
	// need pin to verify
	// need accountNumber to send to
	// need amount
	// need description for transaction's purpose
	// after transaction the history will be save to database
	String transferByUserId(Long id, String pin, String accountNumber, Double amount, String description);

	// when user need to withdraw money from account
	// they have to go to local Branch and carry out a transaction for a specific
	// amount of money to be transferred to BranchAccount
	// after local Branch receives the amount of money, they return the actual paper
	// money to the customer
	// need pin to verify
	// need accountNumber to send to
	// need amount
	// need description for transaction's purpose
	// after transaction the history will be save to database
	String withdrawByUserId(Long id, String pin, String accountNumber, Double amount, String description); 

	
	//in real world , user gives money to Branch and
	// send money
	// to
	// branchAccount
	// in order
	// to
	// withdraw
	// money
	// Branch transfer money to account user
	// AccountDto deposit(Long id); 
	
	

}
