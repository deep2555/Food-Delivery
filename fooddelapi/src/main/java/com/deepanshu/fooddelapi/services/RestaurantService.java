package com.deepanshu.fooddelapi.services;

import java.util.List;
import java.util.Optional;

import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.model.RestaurantRequestModel;

public interface RestaurantService {

	// various method related restarant service
	
	public RestaurantDTO createRestaurant(RestaurantRequestModel restaurantRequestModel);
	public List<RestaurantDTO> fetchRestaurantdata();
	public Optional<RestaurantDTO> fetchRestaurantDetailsById(int id);
}
