package com.deepanshu.fooddelapi.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.services.RestaurantServiceImpl;

// here is the controller to controller the restaurant
public class RestaurantController {

	@Autowired
	private RestaurantServiceImpl restaurantServiceImpl;
	
	
	
	
	
	// method to create or add the list of restaurant
	@PostMapping
	public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
		System.out.println("inside the add Restaurant method ");
		System.out.println("fething the restaurant details: "+ restaurantDTO.toString());
		RestaurantDTO result =  restaurantServiceImpl.createRestaurant(restaurantDTO);
		return new ResponseEntity<RestaurantDTO> (result, HttpStatus.CREATED);
		
		
	}
}
