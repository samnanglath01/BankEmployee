package com.ha.java.dto;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	private String password;
	
	private String phoneNumber;
	
	private String email;
	
	private String address;
	
	private String birthday;
	
	private LocalDateTime createOn;
	
	private Long branchId;
}
