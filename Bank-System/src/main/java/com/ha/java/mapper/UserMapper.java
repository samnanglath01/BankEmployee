package com.ha.java.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ha.java.dto.UserDto;
import com.ha.java.entity.Branch;
import com.ha.java.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(target = "branch.id", source = "branchId")
	User toUser(UserDto userDto);
	
	@Mapping(target = "branchId", source = "branch.id")
	UserDto toUserDto(User user);
	
}
