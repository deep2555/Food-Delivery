package com.deepanshu.fooddelapi.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepanshu.fooddelapi.model.UsersDTO;
import com.deepanshu.fooddelapi.services.UserServiceImpl;

@RestController
public class UserController {


	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("/createUser")
	public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO){
		
		System.out.println("inside the add user method ");
		System.out.println("fething the user details: "+ usersDTO.toString());
		UsersDTO userResult =   userServiceImpl.createUser(usersDTO);		
		return new ResponseEntity<UsersDTO>(userResult, HttpStatus.CREATED);
		
	}
}
