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
import com.ha.java.service.UserAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

	private final UserAccountRepository userAccountRepository;
	private final TransactionService transactionService;
	private final BranchAccountRepository branchAccountRepository;
	private final BranchTransactionRepository branchTransactionRepository;

	@Override
	public AccountDto postAccount(AccountDto accountDto) {

		UserAccount account = AccountMapper.INSTANCE.toUserAccount(accountDto);

		return AccountMapper.INSTANCE.toUserAccountDto(userAccountRepository.save(account));
	}

	@Override
	public AccountDto getAccountByUserId(Long id) {
		UserAccount account = userAccountRepository.findByUserId(id)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("NOT FOUND ID: %d", id)));
		return AccountMapper.INSTANCE.toUserAccountDto(account);
	}

	@Override
	public AccountDto putPinAccountByUserId(Long id, String newPin) {
		UserAccount userAccount = userAccountRepository.findByUserId(id)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("NOT FOUND ID: %d", id)));

		userAccount.setPin(newPin);

		return AccountMapper.INSTANCE.toUserAccountDto(userAccountRepository.save(userAccount));
	}

	@Override
	public String transferByUserId(Long id, String pin, String accountNumber, Double amount, String description) {
		UserAccount userAccountA = userAccountRepository.findByUserId(id)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("NOT FOUND ID: %d", id)));

		if (!(userAccountA.getAccountNumber().equals(accountNumber))) {
			if (userAccountA.getPin().equals(pin)) {
				if (userAccountA.getBalance() >= amount) {

					UserAccount userAccountB = userAccountRepository.findByAccountNumber(accountNumber)
							.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Account Number is not Correct"));

					Double minusBalance = userAccountA.getBalance() - amount;
					Double sumBalance = userAccountB.getBalance() + amount;

					userAccountA.setBalance(minusBalance);
					userAccountB.setBalance(sumBalance);

					userAccountRepository.save(userAccountB);
					userAccountRepository.save(userAccountA);

					Transaction transactionA = Transaction.builder().amount(amount).balance(minusBalance)
							.description(description).status("transfer to other").toAccNumber(accountNumber)
							.userAccount(userAccountA).build();

					Transaction transactionB = Transaction.builder().amount(amount).balance(sumBalance)
							.description(description).status("Receive from other")
							.toAccNumber(userAccountA.getAccountNumber()).userAccount(userAccountB).build();

					transactionService.postTransaction(transactionA);
					transactionService.postTransaction(transactionB);

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
	public String withdrawByUserId(Long id, String pin, String accountNumber, Double amount, String description) {
		UserAccount userAccountA = userAccountRepository.findByUserId(id)
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("NOT FOUND ID: %d", id)));

		if (!(userAccountA.getAccountNumber().equals(accountNumber))) {
			if (userAccountA.getPin().equals(pin)) {
				if (userAccountA.getBalance() >= amount) {

					BranchAccount userAccountB = branchAccountRepository.findByAccountNumber(accountNumber)
							.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Account Number is not Correct"));
					
					Double minusBalance = userAccountA.getBalance() - amount;
					Double sumBalance = userAccountB.getBalance() + amount;

					userAccountA.setBalance(minusBalance);
					userAccountB.setBalance(sumBalance);

					branchAccountRepository.save(userAccountB);
					userAccountRepository.save(userAccountA);

					Transaction transactionA = Transaction.builder().amount(amount).balance(minusBalance)
							.description(description).status("transfer to other").toAccNumber(accountNumber)
							.userAccount(userAccountA).build();

					BranchTransaction transactionB = BranchTransaction.builder().amount(amount).balance(sumBalance)
							.description(description).status("Receive from other")
							.toAccNumber(userAccountA.getAccountNumber())
							.branchAccount(userAccountB).build();

					transactionService.postTransaction(transactionA);
					branchTransactionRepository.save(transactionB);

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

//	@Override
//	public AccountDto deposit(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
