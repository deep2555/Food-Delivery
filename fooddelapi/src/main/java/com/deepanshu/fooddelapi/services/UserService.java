package com.deepanshu.fooddelapi.services;


import java.util.Optional;

import com.deepanshu.fooddelapi.model.UsersDTO;

public interface UserService {

	
	// various method related user service
	
	public UsersDTO createUser(UsersDTO usersDTO);
	public UsersDTO loginUser(String userEmail, String userPassword);
	public Optional<UsersDTO>  fetchUserByEmail(String email);
	
	
}
