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

import com.ha.java.dto.AdminDto;
import com.ha.java.service.AdminService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

	private final AdminService adminService;
	
	@PostMapping
	public ResponseEntity<AdminDto> postAdmin(@RequestBody AdminDto adminDto){
		
		return new ResponseEntity<AdminDto>(adminService.postAdmin(adminDto), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AdminDto> getAdmin(@PathVariable Long id){
		
		return new ResponseEntity<AdminDto>(adminService.getAdmin(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<AdminDto> putAdmin(@PathVariable("id") Long adminId, @RequestBody AdminDto adminDto){
		
		return new ResponseEntity<AdminDto>(adminService.putAdmin(adminId, adminDto), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public String deleteAdmin(@PathVariable Long id) {
		
		return adminService.deleteAdmin(id);
	}
	
}
