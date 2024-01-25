package com.ha.java.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ha.java.dto.BranchDto;
import com.ha.java.dto.UserDto;
import com.ha.java.service.BranchService;
import com.ha.java.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("branch")
@AllArgsConstructor
public class BranchController {

	private final BranchService branchService;
	private final UserService userService;
	
	@PostMapping("{pinForAcc}")
	public ResponseEntity<BranchDto> postBranch(@RequestBody BranchDto branchDto, @PathVariable String pinForAcc){
		
		return new ResponseEntity<BranchDto>(branchService.postBranch(branchDto, pinForAcc), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<BranchDto> getBranch(@PathVariable Long id){
		
		return new ResponseEntity<BranchDto>(branchService.getBranch(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<BranchDto> putBranch(@PathVariable("id") Long branchId, @RequestBody BranchDto branchDto){
		
		return new ResponseEntity<BranchDto>(branchService.putBranch(branchId, branchDto), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<BranchDto> deleteBranch(@PathVariable Long id){
		
		return new ResponseEntity<BranchDto>(branchService.deleteBranch(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/users")
	public ResponseEntity<List<UserDto>> getUsersByBranchId(@PathVariable Long id){
		
		return new ResponseEntity<List<UserDto>>(userService.getUsers(id), HttpStatus.OK);
	}
	
	
	
	
}
