package com.ha.java.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ha.java.dto.AccountDto;
import com.ha.java.dto.UserDto;
import com.ha.java.entity.UserAccount;
import com.ha.java.entity.Branch;
import com.ha.java.entity.User;
import com.ha.java.exception.ApiException;
import com.ha.java.mapper.AccountMapper;
import com.ha.java.mapper.UserMapper;
import com.ha.java.repository.BranchRepository;
import com.ha.java.repository.UserRepository;
import com.ha.java.service.UserAccountService;
import com.ha.java.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final BranchRepository branchRepository;
	private final UserAccountService accountService;
	
	@Override
	public UserDto postUser(UserDto userDto, String pinForAcc) {
		User toUser = UserMapper.INSTANCE.toUser(userDto);
		
		User user = userRepository.save(toUser);
		
		UserDto getUserDto = UserMapper.INSTANCE.toUserDto(user);
		
		UserAccount account = UserAccount.builder()
									.balance(0.00)
									.accountNumber(user.getPhoneNumber())
									.pin(pinForAcc)
									.active("open")
									.user(user)
									.build();
		
		AccountDto toAccountDto = AccountMapper.INSTANCE.toUserAccountDto(account);
		
		accountService.postAccount(toAccountDto);
		
		return getUserDto;
	}

	@Override
	public UserDto getUser(Long id) {
		
		User user = userRepository.findById(id)
						.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("NOT FOUND ID: %d", id)));
		
		return UserMapper.INSTANCE.toUserDto(user);
	}

	@Override
	public UserDto putUser(Long id, UserDto userDto) {
		User user = userRepository.findById(id)
						.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("NOT FOUND ID: %d", id)));
		
		Branch branch = branchRepository.findById(user.getBranch().getId())
				.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("NOT FOUND ID: %d", id)));
		
		user.setBranch(branch);

		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setGender(userDto.getGender());
		user.setPassword(userDto.getPassword());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setEmail(userDto.getEmail());
		user.setAddress(userDto.getAddress());
		user.setBirthday(userDto.getBirthday());
		user.setCreateOn(userDto.getCreateOn());
		
		return UserMapper.INSTANCE.toUserDto(userRepository.save(user));
	}

	@Override
	public UserDto deleteUser(Long id) {

		UserDto userDto = getUser(id);
		
		if( userDto != null) {
			userRepository.deleteById(id);
			return userDto;
		}
		else {
			return null;
		}
	}

	@Override
	public List<UserDto> getUsers(Long branchId) {

		List<User> users = userRepository.findByBranchId(branchId);
		
		List<UserDto> userDtos = users.stream()
				.map(user -> UserMapper.INSTANCE.toUserDto(user))
				.collect(Collectors.toList());
		
		return userDtos;
	}

}
