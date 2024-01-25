package com.ha.java.service;

import com.ha.java.dto.BranchDto;

public interface BranchService {

	//Create Branch and also create BranchAccount
	//branchDto for creating Branch
	//pinForAcc for creating account's pin
	BranchDto postBranch(BranchDto branchDto, String pinForAcc);
	
	
	//get Branch by branch id
	BranchDto getBranch(Long id);
	
	
	//update Branch by branch id
	BranchDto putBranch(Long id, BranchDto branchDto);
	
	
	//delete Branch by branch id
	BranchDto deleteBranch(Long id);
	
}
