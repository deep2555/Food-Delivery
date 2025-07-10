package com.deepanshu.fooddelapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.repo.RestaurantRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepo restaurantRepo;

	// here to add the logic or business logic as well
	@Override
	public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) { 
		System.out.println("here inside creta restaurant service "+ restaurantDTO.toString());
		validateRestaurantFields(restaurantDTO);
		return saveToDB(restaurantDTO);
		
	}

	private RestaurantDTO saveToDB(RestaurantDTO restaurantDTO) {
		// here to save the restaurant inside the database
		System.out.println("inside save to db restaurant");
		return restaurantRepo.save(restaurantDTO);
		
	}

	private void validateRestaurantFields(RestaurantDTO restaurantDTO) {
		// here is to validate the restaurant
		System.out.println("inside validation restaurant");
		if(restaurantDTO.getRestaurantName().equalsIgnoreCase(null) || restaurantDTO.getRestaurantName().isBlank()) {
			throw new IllegalArgumentException("Name is required");
		}
		
	}

}
