package com.deepanshu.fooddelapi.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.fooddelapi.model.UserRole;
import com.deepanshu.fooddelapi.model.UsersDTO;
import com.deepanshu.fooddelapi.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	public static final Logger customLogger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;

	@Override
	public UsersDTO createUser(UsersDTO usersDTO) {
		customLogger.debug("inside create user service: {}", usersDTO);
		// Set default role if null (for testing)
		if (usersDTO.getUserRole() == null) {
			usersDTO.setUserRole(UserRole.CUSTOMER); // or whatever default enum you have
		}
		return userRepo.save(usersDTO);
	}

	@Override
	public UsersDTO loginUser(String userEmail, String userPassword) {
		customLogger.debug("here inside login user service " + userEmail);
		// fetch the user from the db
		UsersDTO fetchUser = userRepo.findByUserMail(userEmail);
		customLogger.debug("the value od userName :{} " , fetchUser);

		// check the valid user
		if (fetchUser == null) {
			customLogger.warn("user not found with this email:{}", userEmail);
			throw new RuntimeException("User not found");
		}

		// check password
		if (!fetchUser.getUserPassword().equalsIgnoreCase(userPassword)) {
			customLogger.warn("password is invalid");
			throw new RuntimeException("Invalid password");
		}
		customLogger.debug("outside all blocks with value:" + fetchUser);
		return fetchUser;
	}

	@Override
	public Optional<UsersDTO> fetchUserByEmail(String email) {
		customLogger.debug("inside the fetch user by email service method" + email);
		UsersDTO fetchUserByEmailResult = userRepo.findByUserMail(email);
		customLogger.debug("the user we fetch:" + fetchUserByEmailResult);

		// check the valid user
		if (fetchUserByEmailResult == null) {
			customLogger.warn("user not found with this email:{}", email);
			throw new RuntimeException("User not found");
		}

		return Optional.ofNullable(fetchUserByEmailResult); // check this spot later
	}

}
