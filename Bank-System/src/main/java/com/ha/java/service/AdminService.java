package com.ha.java.service;

import com.ha.java.dto.AdminDto;

public interface AdminService {
	
	AdminDto postAdmin(AdminDto adminDto);
	
	AdminDto getAdmin(Long id);
	
	AdminDto putAdmin(Long id, AdminDto adminDto);
	
	String deleteAdmin(Long id);

}
