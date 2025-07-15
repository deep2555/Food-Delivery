package com.deepanshu.fooddelapi.services;

import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.model.RestaurantRequestModel;

public interface RestaurantService {

	// various method related restarant service
	
	public RestaurantDTO createRestaurant(RestaurantRequestModel restaurantRequestModel);
}
