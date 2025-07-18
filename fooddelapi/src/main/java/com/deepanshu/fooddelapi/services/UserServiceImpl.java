package com.deepanshu.fooddelapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.fooddelapi.model.UserRole;
import com.deepanshu.fooddelapi.model.UsersDTO;
import com.deepanshu.fooddelapi.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UsersDTO createUser(UsersDTO usersDTO) {
		System.out.println("here inside creta user service " + usersDTO.toString());
		// Set default role if null (for testing)
		if (usersDTO.getUserRole() == null) {
			usersDTO.setUserRole(UserRole.CUSTOMER); // or whatever default enum you have
		}
		return userRepo.save(usersDTO);
	}

	@Override
	public UsersDTO loginUser(String userEmail, String userPassword) {
		System.out.println("here inside login user service " + userEmail + " : " + userPassword);

		// fetch the user from the db
		UsersDTO fetchUser = userRepo.findByUserMail(userEmail);
		System.out.println("the value od userName : " + fetchUser);
		// check the valid user
		if (fetchUser == null) {
			System.out.println("inside if statement fetch user service");
			throw new RuntimeException("User not found");
		}

		// check password
		if (!fetchUser.getUserPassword().equalsIgnoreCase(userPassword)) {
			System.out.println("inside if statement password check");
			throw new RuntimeException("Invalid password");
		}
		System.out.println("outside all blocks with value:"+ fetchUser);
		return fetchUser;
	}

	@Override
	public Optional<UsersDTO> fetchUserByEmail(String email) {
		System.out.println("inside the fetch user by email service method"+ email);
		UsersDTO fetchUserByEmailResult = userRepo.findByUserMail(email);
		System.out.println("the user we fetch:" + fetchUserByEmailResult);
		// check the valid user
				if (fetchUserByEmailResult == null) {
					System.out.println("inside if statement fetch user service");
					throw new RuntimeException("User not found");
				}
		
		return Optional.ofNullable(fetchUserByEmailResult); // check this spot later 
	}

}
