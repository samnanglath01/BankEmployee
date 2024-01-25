package com.ha.java.controller;


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

import com.ha.java.service.UserService;
import com.ha.java.dto.UserDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping("{pinForAcc}")
	public ResponseEntity<UserDto> postUser(@RequestBody UserDto userDto, @PathVariable String pinForAcc){
		
		return new ResponseEntity<UserDto>(userService.postUser(userDto, pinForAcc), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long id){
		
		return new ResponseEntity<UserDto>(userService.getUser(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<UserDto> putUser(@PathVariable Long id, @RequestBody UserDto userDto){
		
		return new ResponseEntity<UserDto>(userService.putUser(id, userDto), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
		
		return new ResponseEntity<UserDto>(userService.deleteUser(id), HttpStatus.OK);
	}
	
	
}
