package com.deepanshu.fooddelapi.services;

import java.util.Optional;

import com.deepanshu.fooddelapi.model.MenuItemsDTO;
import com.deepanshu.fooddelapi.model.MenuItemsRequestHandlerModel;

public interface MenuItemsService {

	public MenuItemsDTO createMenuItems(MenuItemsRequestHandlerModel itemsRequestHandlerModel);
	public Optional<MenuItemsDTO> fetchMenuItemById(int restaurantId);
}
