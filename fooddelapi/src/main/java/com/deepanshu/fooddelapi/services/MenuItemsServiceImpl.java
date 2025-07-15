package com.deepanshu.fooddelapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.fooddelapi.model.MenuItemsDTO;
import com.deepanshu.fooddelapi.model.MenuItemsRequestHandlerModel;
import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.repo.ItemsRepo;
import com.deepanshu.fooddelapi.repo.RestaurantRepo;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {

	@Autowired
	private ItemsRepo itemsRepo;
	
	@Autowired
	private RestaurantRepo restaurantRepo;

	@Override
	public MenuItemsDTO createMenuItems(MenuItemsRequestHandlerModel itemsRequestHandlerModel) {
		System.out.println("here inside the menuitems create menu items");
		System.out.println(itemsRequestHandlerModel.toString());
		// here we receive the request body in request handler and than add into
		// original Dto model class
		RestaurantDTO fetchMenu = restaurantRepo.findById(itemsRequestHandlerModel.getRestaurantDetailId())
				.orElseThrow(() -> new RuntimeException("no list found"));
		// initiate the real dto
		MenuItemsDTO menuItemsDTO = new MenuItemsDTO();
		menuItemsDTO.setItemName(itemsRequestHandlerModel.getItemName());
		menuItemsDTO.setItemPrice(itemsRequestHandlerModel.getItemPrice());
		menuItemsDTO.setItemDescription(itemsRequestHandlerModel.getItemDescription());
		menuItemsDTO.setItemPrice(itemsRequestHandlerModel.getItemPrice());
		menuItemsDTO.setCategory(itemsRequestHandlerModel.getCategory());
		menuItemsDTO.setRestaurant(fetchMenu);

		return itemsRepo.save(menuItemsDTO);
	}

}
