package com.deepanshu.fooddelapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepanshu.fooddelapi.model.UsersDTO;

// here is the repo class for the user to connect with db connection through jpa

@Repository
public interface UserRepo extends JpaRepository<UsersDTO, Integer>{

	
	// some methods for later on 
}
