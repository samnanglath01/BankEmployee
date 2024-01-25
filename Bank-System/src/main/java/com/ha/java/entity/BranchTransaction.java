package com.ha.java.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "branch_transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime date;
	
	@Column(nullable = false)
	private Double amount;
	
	@Column(nullable = false)
	private Double balance;
	
	private String description;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private String toAccNumber;
	
	@ManyToOne
	@JoinColumn(name = "branch_account")
	private BranchAccount branchAccount;
	
}
