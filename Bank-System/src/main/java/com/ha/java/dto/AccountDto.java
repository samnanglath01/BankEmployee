package com.ha.java.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	private Long id;
	
	private Double balance;
	
	private String accountNumber;
	
	private String pin;
	
	private String active;
	
	private LocalDateTime date;
	
	private Long userId;
	
	private Long branchId;
}
