package com.deepanshu.fooddelapi.contollers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deepanshu.fooddelapi.DTO.UserDataTransferObject;
import com.deepanshu.fooddelapi.model.UsersDTO;
import com.deepanshu.fooddelapi.services.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	

	@PostMapping("/createUser")
	public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO) {

		System.out.println("inside the add user method ");
		System.out.println("fething the user details: " + usersDTO.toString());
		UsersDTO userResult = userServiceImpl.createUser(usersDTO);
		return new ResponseEntity<UsersDTO>(userResult, HttpStatus.CREATED);

	}

	// here the controller method to validate the user and login the user
	@PostMapping("/loginUser")
	public ResponseEntity<?> loginUser(@RequestBody UserDataTransferObject userDataTransferObject) {
		System.out.println("inside Login user method");
		System.out.println("user" + userDataTransferObject.toString());

		try {
			UsersDTO loginResult = userServiceImpl.loginUser(userDataTransferObject.getUserEmail(),
					userDataTransferObject.getUserPassword());
			return  ResponseEntity.ok(loginResult);
		} catch (RuntimeException e) {
			return new ResponseEntity<UsersDTO>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	// here the method to fetch the user when login 
	// we are receiving the email of the user from the fronted and based 
	// on that we have to fetch the username and its detail
	@GetMapping("/profile")
	public ResponseEntity<UsersDTO> fetchUserByMail(@RequestParam String email){
		System.out.println("here inside the fetch user by mail  method:");
		System.out.println("we got from frontend:"+ email);
		
		Optional<UsersDTO> resultFetchUser = userServiceImpl.fetchUserByEmail(email);
		return new ResponseEntity<>(resultFetchUser.get(),HttpStatus.OK); // 
		
	}
}
