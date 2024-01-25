package com.ha.java.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ha.java.dto.AdminDto;
import com.ha.java.entity.Admin;
import com.ha.java.exception.ApiException;
import com.ha.java.mapper.AdminMapper;
import com.ha.java.repository.AdminRepository;
import com.ha.java.service.AdminService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final AdminRepository adminRepository;

	@Override
	public AdminDto postAdmin(AdminDto adminDto) {
		
		Admin admin = AdminMapper.INSTANCE.toAdmin(adminDto);
		AdminDto getAdminDto = AdminMapper.INSTANCE.toAdminDto(adminRepository.save(admin));
		
		return getAdminDto;
	}

	@Override
	public AdminDto getAdmin(Long id) {
		
		//Admin admin = adminRepository.findById(id).orElse(null);
		
		Admin admin = adminRepository.findById(id)
					  .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("NOT FOUND ID: %d", id)) );
		
		return AdminMapper.INSTANCE.toAdminDto(admin);
	}

	@Override
	public AdminDto putAdmin(Long id, AdminDto adminDto) {
		
		//Admin admin = adminRepository.findById(id).orElse(null);
		
		Admin admin = adminRepository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("NOT FOUND ID: %d", id)));
		
		admin.setPassword(adminDto.getPassword());
		admin.setUsername(adminDto.getUsername());
		
		AdminDto getAdminDto = AdminMapper.INSTANCE.toAdminDto(adminRepository.save(admin));
		return getAdminDto;
	}

	@Override
	public String deleteAdmin(Long id) {
		
		AdminDto adminDto = getAdmin(id);
		Admin admin = AdminMapper.INSTANCE.toAdmin(adminDto);
		if(admin != null) {
			adminRepository.delete(admin);
			return "Delete Successfully !";
		}
		else {
			return "NOT EXIST";
		}
		
	}

}
