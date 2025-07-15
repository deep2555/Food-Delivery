package com.deepanshu.fooddelapi.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.model.RestaurantRequestModel;
import com.deepanshu.fooddelapi.model.UsersDTO;
import com.deepanshu.fooddelapi.repo.RestaurantRepo;
import com.deepanshu.fooddelapi.repo.UserRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepo restaurantRepo;

	@Autowired
	private UserRepo userRepo;

	
	// here to add the logic or business logic as well
	@Override
	public RestaurantDTO createRestaurant(RestaurantRequestModel restaurantRequestModel) {
		System.out.println("here inside creta restaurant service " + restaurantRequestModel.toString());
		validateRestaurantFields(restaurantRequestModel);

		/*
		 * here is the process for fetching the owner by id passed through restaurant
		 * body than pass it in restaurant dto than save it in db trial based request
		 */
		UsersDTO fetchUserForRestaurant = userRepo.findById(restaurantRequestModel.getOwnerId())
				.orElseThrow(() -> new RuntimeException("Owner not found"));
		
		System.out.println("owner details" + fetchUserForRestaurant.toString());
		// after fetching set it into orignal dto model class
		RestaurantDTO restaurantDTO = new RestaurantDTO();
		restaurantDTO.setRestaurantName(restaurantRequestModel.getRestaurantName());
		restaurantDTO.setRestaurantDescription(restaurantRequestModel.getRestaurantDescription());
		restaurantDTO.setRestaurantCuisineType(restaurantRequestModel.getRestaurantCuisineType());
		restaurantDTO.setRestaurantContactInfo(restaurantRequestModel.getRestaurantContactInfo());
		restaurantDTO.setRestaurantAddress(restaurantRequestModel.getRestaurantAddress());
		restaurantDTO.setRestaurantOpeningHours(restaurantRequestModel.getRestaurantOpeningHours());
		restaurantDTO.setRestOwner(fetchUserForRestaurant);
		
		return saveToDB(restaurantDTO);

	}

	private RestaurantDTO saveToDB(RestaurantDTO restaurantDTO) {
		// here to save the restaurant inside the database
		System.out.println("inside save to db restaurant");
		return restaurantRepo.save(restaurantDTO);

	}

	private void validateRestaurantFields(RestaurantRequestModel restaurantRequestModel) {
		// here is to validate the restaurant
		System.out.println("inside validation restaurant");
		if (restaurantRequestModel.getRestaurantName().equalsIgnoreCase(null) || restaurantRequestModel.getRestaurantName().isBlank()) {
			throw new IllegalArgumentException("Name is required");
		}

	}

}
