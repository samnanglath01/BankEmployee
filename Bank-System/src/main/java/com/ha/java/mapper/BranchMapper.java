package com.ha.java.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ha.java.dto.BranchDto;
import com.ha.java.entity.Branch;

@Mapper
public interface BranchMapper {

	BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);
	
	Branch toBranch(BranchDto branchDto);
	
	BranchDto toBranchDto(Branch branch);
	
}
