package com.javatechie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{

	Optional<UserInfo> findByName(String username);
}
