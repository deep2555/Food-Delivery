package com.deepanshu.fooddelapi.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.model.RestaurantRequestModel;
import com.deepanshu.fooddelapi.services.RestaurantServiceImpl;

// here is the controller to controller the restaurant
@RestController
public class RestaurantController {

	@Autowired
	private RestaurantServiceImpl restaurantServiceImpl;

	// method to create or add the list of restaurant

	@PostMapping("/addRestaurant")
	public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantRequestModel restaurantRequestModel) {
		System.out.println("inside the add Restaurant method ");
		System.out.println("fething the restaurant details: " + restaurantRequestModel.toString());
		
		RestaurantDTO result = restaurantServiceImpl.createRestaurant(restaurantRequestModel);
		return new ResponseEntity<RestaurantDTO>(result, HttpStatus.CREATED);

	}

}
