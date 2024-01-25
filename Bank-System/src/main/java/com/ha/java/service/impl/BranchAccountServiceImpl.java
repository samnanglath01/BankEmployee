package com.ha.java.service.impl;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ha.java.dto.AccountDto;
import com.ha.java.entity.BranchAccount;
import com.ha.java.entity.BranchTransaction;
import com.ha.java.entity.Transaction;
import com.ha.java.entity.UserAccount;
import com.ha.java.exception.ApiException;
import com.ha.java.mapper.AccountMapper;
import com.ha.java.repository.BranchAccountRepository;
import com.ha.java.repository.BranchTransactionRepository;
import com.ha.java.repository.UserAccountRepository;
import com.ha.java.service.BranchAccountService;
import com.ha.java.service.TransactionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BranchAccountServiceImpl implements BranchAccountService{
	
	private final BranchAccountRepository branchAccountRepository;
	private final UserAccountRepository userAccountRepository;
	private final TransactionService transactionService;
	private final BranchTransactionRepository branchTransactionRepository;
	
	@Override
	public AccountDto postBranchAccount(AccountDto accountDto) {
	
		BranchAccount branchAccount = AccountMapper.INSTANCE.toBranchAccount(accountDto);
		
		AccountDto getAccountDto = AccountMapper.INSTANCE.toBranchAccountDto(branchAccountRepository.save(branchAccount));
	
	return getAccountDto;
	}

	@Override
	public AccountDto getAccountByBranchId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDto putPinAccountByBranchId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDto withdraw(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDto deposit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String transfer(Long id, String pin, String accountNumber, Double amount, String description) {
			BranchAccount branchAccountA = branchAccountRepository.findByBranchId(id)
					.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("NOT FOUND ID: %d", id)));

			if (!(branchAccountA.getAccountNumber().equals(accountNumber))) {
				if (branchAccountA.getPin().equals(pin)) {
					if (branchAccountA.getBalance() >= amount) {

						UserAccount userAccountB = userAccountRepository.findByAccountNumber(accountNumber)
								.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Account Number is not Correct"));
						
						Double minusBalance = branchAccountA.getBalance() - amount;
						Double sumBalance = userAccountB.getBalance() + amount;

						branchAccountA.setBalance(minusBalance);
						userAccountB.setBalance(sumBalance);

						userAccountRepository.save(userAccountB);
						branchAccountRepository.save(branchAccountA);
						
						BranchTransaction transactionA = BranchTransaction.builder().amount(amount).balance(minusBalance)
								.description(description).status("transfer to other")
								.toAccNumber(userAccountB.getAccountNumber())
								.branchAccount(branchAccountA).build();

						Transaction transactionB = Transaction.builder().amount(amount).balance(sumBalance)
								.description(description).status("Receive from other").toAccNumber(branchAccountA.getAccountNumber())
								.userAccount(userAccountB).build();

						
						transactionService.postTransaction(transactionB);
						branchTransactionRepository.save(transactionA);

						return "Successful transfer to account number: #" + userAccountB.getAccountNumber();
					} else {
						return "insufficient Balance, Transaction failed";
					}
				}

				return "PIN is not correct, Transaction failed";
			} else {
				return "Account Number is not Correct";
			}
		}

	@Override
	public AccountDto getAccountByAccountNumber(String accountNumber) {
		AccountDto accountDto = AccountMapper.INSTANCE.toBranchAccountDto(branchAccountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "NOT FOUND")));
		return accountDto;
	}

	

}
