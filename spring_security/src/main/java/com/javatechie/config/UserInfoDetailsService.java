package com.javatechie.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.javatechie.entity.UserInfo;
import com.javatechie.repository.UserInfoRepository;

@Component
public class UserInfoDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
		
		UserInfo getUserInfo = userInfo.orElseThrow(() -> new UsernameNotFoundException("username is not found :"+ username));
										
		UserInfoDetails userInfoDetails = new UserInfoDetails(getUserInfo);
		
		
		return userInfoDetails;
	}

}
