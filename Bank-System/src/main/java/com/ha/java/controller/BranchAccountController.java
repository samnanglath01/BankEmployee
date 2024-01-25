package com.ha.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ha.java.service.BranchAccountService;
import com.ha.java.utils.TransferObject;

@RestController
@RequestMapping("branchaccount")
public class BranchAccountController {

	@Autowired
	private BranchAccountService branchAccountService;
	
	@PutMapping("transfer/{id}")
	public ResponseEntity<String> transferToUser(@PathVariable Long id, @RequestBody TransferObject transferObject){
		
		return new ResponseEntity<String>(branchAccountService.transfer(id, transferObject.getPin(),transferObject.getAccountNumber(), transferObject.getBalance(), transferObject.getDescription()), HttpStatus.OK);
	}
}
