package com.ha.java.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_accounts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;
	
	@Column(nullable = false)
	private Double balance; //set default value
	
	@Column(name = "account_number", nullable = false, unique = true)
	private String accountNumber;
	
	@Column(nullable = false)
	private String pin;
	
	@Column(nullable = false)
	private String active;
	
	@CreationTimestamp
	private LocalDateTime date;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	
}
