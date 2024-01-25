package com.ha.java.service;

import com.ha.java.dto.AccountDto;

public interface BranchAccountService {

	// create BranchAccount
	AccountDto postBranchAccount(AccountDto accountDto);

	// get BranchAccount by accNumber
	AccountDto getAccountByAccountNumber(String accountNumber);

	// get BranchAccount by Branch id
	AccountDto getAccountByBranchId(Long id);

	// update pin of branchAccount by branch id
	AccountDto putPinAccountByBranchId(Long id);
	
	//get all BranchAccount

	AccountDto withdraw(Long id);

	AccountDto deposit(Long id);

	// when a customer wishes to deposit money into their account,
	// they go to local Branch and hand over the actual cash to the branch
	// after that, the branch transfers the money into their account
	// need pin to verify
	// need accountNumber to send to
	// need amount
	// need description for transaction's purpose
	// after transaction the history will be save to database
	String transfer(Long id, String pin, String accountNumber, Double amount, String description);

}
