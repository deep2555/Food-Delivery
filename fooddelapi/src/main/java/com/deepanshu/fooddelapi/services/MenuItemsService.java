package com.deepanshu.fooddelapi.services;

import java.util.List;
import java.util.Map;

import com.deepanshu.fooddelapi.model.MenuItemsDTO;
import com.deepanshu.fooddelapi.model.MenuItemsRequestHandlerModel;

public interface MenuItemsService {

	public MenuItemsDTO createMenuItems(MenuItemsRequestHandlerModel itemsRequestHandlerModel);
	public List<Map<String, Object>> fetchMenuItemById(int restaurantId);
}
