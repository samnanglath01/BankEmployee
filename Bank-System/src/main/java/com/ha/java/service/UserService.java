package com.ha.java.service;

import java.util.List;

import com.ha.java.dto.UserDto;

public interface UserService {
	
	
	//create User also create UserAccount
	//userDto for creating User
	//pinForAcc for creating userAccount
	UserDto postUser(UserDto userDto, String pinForAcc);
	
	//get User by user id
	UserDto getUser(Long id);
	
	//update User by user id
	UserDto putUser(Long id, UserDto userDto);
	
	//delete User by user id
	UserDto deleteUser(Long id);
	
	//get all User by branch id
	List<UserDto> getUsers(Long branchId);
	
	//get all User
	
}
