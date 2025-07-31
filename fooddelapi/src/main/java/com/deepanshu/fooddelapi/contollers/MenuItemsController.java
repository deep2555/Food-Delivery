package com.deepanshu.fooddelapi.contollers;


import java.util.List;
import java.util.Map;


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
import org.springframework.web.bind.annotation.RestController;

import com.deepanshu.fooddelapi.model.MenuItemsDTO;
import com.deepanshu.fooddelapi.model.MenuItemsRequestHandlerModel;
import com.deepanshu.fooddelapi.services.MenuItemsServiceImpl;



@RestController
@RequestMapping("/api/user/restaurant")
public class MenuItemsController {

	public static final Logger customLogger = LoggerFactory.getLogger(MenuItemsController.class);

	@Autowired
	private MenuItemsServiceImpl menuItemsServiceImpl;
	
	@PostMapping("/createMenuItems")
	public ResponseEntity<MenuItemsDTO> createMenuItemns(@RequestBody MenuItemsRequestHandlerModel itemsRequestHandlerModel){
		customLogger.debug("fething the menu items details: {}", itemsRequestHandlerModel);
		MenuItemsDTO menuResult =   menuItemsServiceImpl.createMenuItems(itemsRequestHandlerModel);		
		return new ResponseEntity<MenuItemsDTO>(menuResult, HttpStatus.CREATED);
		
	}
	
	// here to fetch the menu items with id
	@GetMapping("/menu/{restaurantId}")
	public ResponseEntity<List<Map<String, Object>>> fetchMenuItemsById(@PathVariable int restaurantId){
		customLogger.debug("the id we get is : {}", restaurantId);
		List<Map<String, Object>> menuFetchedResponse = menuItemsServiceImpl.fetchMenuItemById(restaurantId);
		
		return ResponseEntity.ok(menuFetchedResponse);
	}
}
