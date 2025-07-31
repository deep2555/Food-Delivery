package com.deepanshu.fooddelapi.contollers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	// import the logger with org.slf4j
	public static final Logger customLogger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userServiceImpl;
	

	@PostMapping("/createUser")
	public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO) {

		customLogger.info("inside the add user method");
		customLogger.trace("fetch userdetail:"+ usersDTO.toString());

		UsersDTO userResult = userServiceImpl.createUser(usersDTO);
		return new ResponseEntity<UsersDTO>(userResult, HttpStatus.CREATED);

	}

	// here the controller method to validate the user and login the user
	@PostMapping("/loginUser")
	public ResponseEntity<?> loginUser(@RequestBody UserDataTransferObject userDataTransferObject) {
		
		customLogger.info("inside Login user method");
		customLogger.trace("user" + userDataTransferObject.toString());
		
		try {
			UsersDTO loginResult = userServiceImpl.loginUser(userDataTransferObject.getUserEmail(),
					userDataTransferObject.getUserPassword());
			customLogger.debug("the response got from the service: {}", loginResult);
			return  ResponseEntity.ok(loginResult);
		} catch (RuntimeException e) {
			return new ResponseEntity<UsersDTO>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	/*
	 * here the method to fetch the user when login we are receiving the email of
	 * the user from the fronted and based on that we have to fetch the user-name
	 * and its detail
	 */
	@GetMapping("/profile")
	public ResponseEntity<UsersDTO> fetchUserByMail(@RequestParam String email){
		customLogger.debug("here inside the fetch user by mail method");
		customLogger.debug("the email fetch from the frontend:"+ email);
		
		Optional<UsersDTO> resultFetchUser = userServiceImpl.fetchUserByEmail(email);
		return new ResponseEntity<>(resultFetchUser.get(),HttpStatus.OK); // 
		
	}
}
