package com.deepanshu.fooddelapi.services;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.model.RestaurantRequestModel;
import com.deepanshu.fooddelapi.model.UsersDTO;
import com.deepanshu.fooddelapi.repo.RestaurantRepo;
import com.deepanshu.fooddelapi.repo.UserRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	public static final Logger customLogger = LoggerFactory.getLogger(RestaurantServiceImpl.class);


	@Autowired
	private RestaurantRepo restaurantRepo;

	@Autowired
	private UserRepo userRepo;

	
	// here to add the logic or business logic as well
	@Override
	public RestaurantDTO createRestaurant(RestaurantRequestModel restaurantRequestModel) {
		customLogger.debug("here inside creta restaurant service: {} " , restaurantRequestModel);
		validateRestaurantFields(restaurantRequestModel);

		/*
		 * here is the process for fetching the owner by id passed through restaurant
		 * body than pass it in restaurant DTO than save it in DB trial based request
		 */
		UsersDTO fetchUserForRestaurant = userRepo.findById(restaurantRequestModel.getOwnerId())
				.orElseThrow(() -> new RuntimeException("Owner not found"));
		customLogger.debug("owner details : {}", fetchUserForRestaurant);
		// after fetching set it into orignal dto model class
		RestaurantDTO restaurantDTO = new RestaurantDTO();
		restaurantDTO.setRestaurantName(restaurantRequestModel.getRestaurantName());
		restaurantDTO.setRestaurantDescription(restaurantRequestModel.getRestaurantDescription());
		restaurantDTO.setRestaurantCuisineType(restaurantRequestModel.getRestaurantCuisineType());
		restaurantDTO.setRestaurantContactInfo(restaurantRequestModel.getRestaurantContactInfo());
		restaurantDTO.setRestaurantAddress(restaurantRequestModel.getRestaurantAddress());
		restaurantDTO.setRestaurantOpeningHours(restaurantRequestModel.getRestaurantOpeningHours());
		restaurantDTO.setDelieveryTime(restaurantRequestModel.getDelieveryTime());
		restaurantDTO.setPriceRange(restaurantRequestModel.getPriceRange());
		restaurantDTO.setRestOwner(fetchUserForRestaurant);
		
		return saveToDB(restaurantDTO);
	}
	
	
	// here is the method to fetch the details of the restaurant
	@Override
	public List<RestaurantDTO> fetchRestaurantdata() {
		customLogger.debug("inside the fetch restaurant service methd");
		return restaurantRepo.findAll();
	}

	// here we fetch the detail of the restaurant by id
	@Override
	public Optional<RestaurantDTO> fetchRestaurantDetailsById(int id) {
		customLogger.debug("inside fetchRestaurantDetailsById service method with id: {}", id);
		Optional<RestaurantDTO> resultFetchRestaurant = restaurantRepo.findById(id);
		resultFetchRestaurant.stream().forEach(System.out::println);
		return resultFetchRestaurant;
	}
	
	
	// helping methods 
	private RestaurantDTO saveToDB(RestaurantDTO restaurantDTO) {
		// here to save the restaurant inside the database
		customLogger.debug("inside save to db restaurant method");
		return restaurantRepo.save(restaurantDTO);

	}

	private void validateRestaurantFields(RestaurantRequestModel restaurantRequestModel) {
		// here is to validate the restaurant
		customLogger.debug("inside validation restaurant method");
		if (restaurantRequestModel.getRestaurantName().equalsIgnoreCase(null) || restaurantRequestModel.getRestaurantName().isBlank()) {
			throw new IllegalArgumentException("Name is required");
		}

	}




}
