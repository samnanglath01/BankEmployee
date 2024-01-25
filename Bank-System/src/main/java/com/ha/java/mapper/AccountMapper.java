package com.ha.java.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ha.java.dto.AccountDto;
import com.ha.java.entity.BranchAccount;
import com.ha.java.entity.UserAccount;

@Mapper(componentModel = "spring")
public interface AccountMapper {

	AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
	
	@Mapping(target = "user.id", source = "userId")
	UserAccount toUserAccount(AccountDto accountDto);
	
	@Mapping(target = "userId", source = "user.id")
	AccountDto toUserAccountDto(UserAccount account);
	
	
	@Mapping(target = "branch.id", source = "branchId")
	BranchAccount toBranchAccount(AccountDto accountDto);
	
	@Mapping(target = "branchId", source = "branch.id")
	AccountDto toBranchAccountDto(BranchAccount account);
	
}
