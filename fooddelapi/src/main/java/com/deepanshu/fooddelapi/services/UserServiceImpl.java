package com.deepanshu.fooddelapi.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.deepanshu.fooddelapi.model.UsersDTO;
import com.deepanshu.fooddelapi.repo.UserRepo;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UsersDTO createUser(UsersDTO usersDTO) {
		System.out.println("here inside creta user service "+ usersDTO.toString());	
		// check this
		return userRepo.save(usersDTO);
	}

}
