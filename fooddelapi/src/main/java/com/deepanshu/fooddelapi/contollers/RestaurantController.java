package com.deepanshu.fooddelapi.contollers;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.model.RestaurantRequestModel;
import com.deepanshu.fooddelapi.services.RestaurantServiceImpl;

// here is the controller to controller the restaurant
@RestController
@RequestMapping("/api/users/restaurants")
public class RestaurantController {

	// import the custom log 
	public static final Logger customLogger = LoggerFactory.getLogger(RestaurantController.class);
	
	@Autowired
	private RestaurantServiceImpl restaurantServiceImpl;

	// method to create or add the list of restaurant
	@PostMapping("/addRestaurant")
	public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantRequestModel restaurantRequestModel) {
		
		customLogger.debug("inside the add Restaurant method ");
		customLogger.debug("fetch the restaurant details: {}", restaurantRequestModel);
		
		RestaurantDTO result = restaurantServiceImpl.createRestaurant(restaurantRequestModel);
		return new ResponseEntity<RestaurantDTO>(result, HttpStatus.CREATED);

	}
	

	// fetch all the restaurant list and update it on fronted
	@GetMapping("/restaurantsDetail")
	public ResponseEntity<List<RestaurantDTO>> fetchRestaurantDetails() {
		customLogger.debug("inside the fetch restaurant controller method");
		List<RestaurantDTO> fetchResult = restaurantServiceImpl.fetchRestaurantdata();
		customLogger.debug("the result is: {}", fetchResult);	
		
		for(RestaurantDTO checkResult : fetchResult) {
			customLogger.debug(checkResult.toString());
		}
		return new ResponseEntity<List<RestaurantDTO>>(fetchResult, HttpStatus.OK);
	}

	
	// fetch the restaurant details based on the id 
	@GetMapping("/{id}")
	public ResponseEntity<Optional<RestaurantDTO>> fetchRestaurantDetailById(@PathVariable int id){
		customLogger.debug("inside the fetchRestaurantDetailById method with id :"+ id);
		Optional<RestaurantDTO> resultRestaurantById = restaurantServiceImpl.fetchRestaurantDetailsById(id);
		resultRestaurantById.stream().forEach(System.out::println);
		return new ResponseEntity<Optional<RestaurantDTO>>(resultRestaurantById, HttpStatus.OK);
		
	}
}
