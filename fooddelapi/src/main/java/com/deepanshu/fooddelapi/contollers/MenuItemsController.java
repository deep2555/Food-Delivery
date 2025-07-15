package com.deepanshu.fooddelapi.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepanshu.fooddelapi.model.MenuItemsDTO;
import com.deepanshu.fooddelapi.model.MenuItemsRequestHandlerModel;
import com.deepanshu.fooddelapi.services.MenuItemsServiceImpl;


@RestController
public class MenuItemsController {

	@Autowired
	private MenuItemsServiceImpl menuItemsServiceImpl;
	
	@PostMapping("/createMenuItems")
	public ResponseEntity<MenuItemsDTO> createMenuItemns(@RequestBody MenuItemsRequestHandlerModel itemsRequestHandlerModel){
		
		System.out.println("inside the add menu controller method ");
		System.out.println("fething the menu items details: "+ itemsRequestHandlerModel.toString());
		
		MenuItemsDTO menuResult =   menuItemsServiceImpl.createMenuItems(itemsRequestHandlerModel);		
		return new ResponseEntity<MenuItemsDTO>(menuResult, HttpStatus.CREATED);
		
	}
}
