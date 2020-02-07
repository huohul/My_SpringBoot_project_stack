package com.gxweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gxweb.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
}
