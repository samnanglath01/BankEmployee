package com.ha.java.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ha.java.dto.AdminDto;
import com.ha.java.entity.Admin;

@Mapper
public interface AdminMapper {
	
	AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

	Admin toAdmin(AdminDto adminDto);
	
	AdminDto toAdminDto(Admin admin);
}
