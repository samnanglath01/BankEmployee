package com.ha.java.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ha.java.dto.AccountDto;
import com.ha.java.entity.UserAccount;
import com.ha.java.service.UserAccountService;
import com.ha.java.utils.TransferObject;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("useraccount")
@AllArgsConstructor
public class UserAccountController {

	private final UserAccountService accountService;

	@GetMapping("{id}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {

		return new ResponseEntity<AccountDto>(accountService.getAccountByUserId(id), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<AccountDto> updatePin(@PathVariable Long id, @RequestBody UserAccount userAccount) {

		return new ResponseEntity<AccountDto>(accountService.putPinAccountByUserId(id, userAccount.getPin()), HttpStatus.OK);
	}

	@PutMapping("/transfer/{id}")
	public ResponseEntity<String> transferMoney(@PathVariable Long id, @RequestBody TransferObject transferObject) {

		return new ResponseEntity<String>(accountService.transferByUserId(id, transferObject.getPin(),transferObject.getAccountNumber(), transferObject.getBalance(), transferObject.getDescription()), HttpStatus.OK);
	}
	
	@PutMapping("/withdraw/{id}")
	public ResponseEntity<String> withdrawMoney(@PathVariable Long id, @RequestBody TransferObject transferObject) {

		return new ResponseEntity<String>(accountService.withdrawByUserId(id, transferObject.getPin(),transferObject.getAccountNumber(), transferObject.getBalance(), transferObject.getDescription()), HttpStatus.OK);
	}
	
}
