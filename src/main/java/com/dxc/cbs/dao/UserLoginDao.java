package com.dxc.cbs.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.cbs.model.UserLogin;

@Repository
@Transactional
public interface UserLoginDao extends JpaRepository<UserLogin, String> {
	Optional<UserLogin> findByPassword(String password);
}
