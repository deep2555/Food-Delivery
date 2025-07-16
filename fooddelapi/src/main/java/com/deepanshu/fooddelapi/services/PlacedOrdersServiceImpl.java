package com.deepanshu.fooddelapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.fooddelapi.model.MenuItemsDTO;
import com.deepanshu.fooddelapi.model.OrderRequestModelHandler;
import com.deepanshu.fooddelapi.model.OrdersDTO;
import com.deepanshu.fooddelapi.model.OrdersItemsDTO;
import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.model.UsersDTO;
import com.deepanshu.fooddelapi.repo.ItemsRepo;
import com.deepanshu.fooddelapi.repo.OrdersRepo;
import com.deepanshu.fooddelapi.repo.RestaurantRepo;
import com.deepanshu.fooddelapi.repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class PlacedOrdersServiceImpl implements PlacedOrderService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RestaurantRepo restaurantRepo;

	@Autowired
	private ItemsRepo itemsRepo;
	
	@Autowired
	private OrdersRepo ordersRepo;

	@Override
	@Transactional
	public OrdersDTO placedOrder(OrderRequestModelHandler orderRequestModelHandler) {
		// all the business logic for the order placed

		System.out.println("inside the placed order servise method");
		System.out.println("data check: " + orderRequestModelHandler.toString());

		/*
		 * use the java streams functionality 1. fetch the user 2. fetch the restaurant
		 * 3) fetch the menu items
		 */
		UsersDTO fetchUserDetail = userRepo.findById(orderRequestModelHandler.getUserId())
				.orElseThrow(() -> new RuntimeException("Customer not found")); // fetch the user detail

		RestaurantDTO fetchRestaurantDetail = restaurantRepo.findById(orderRequestModelHandler.getRestaurantId())
				.orElseThrow(() -> new RuntimeException("Restaurnat not found")); // fetch the restaurant detail
		
		OrdersDTO ordersDTO = new OrdersDTO(); // initiate the order Dto model to add the details
		ordersDTO.setUserDto(fetchUserDetail); // set the used details in that 
		ordersDTO.setRestaurantDTO(fetchRestaurantDetail); // set the restaurant details in that 
		
		// add oder items means how many items containing the orders
		//getorderscontain items contains the menu item list, quantity, special demand 
		// inside the map function we are fetching the details of the menu items
		
		List<OrdersItemsDTO> items = orderRequestModelHandler.getOrderContainItems().stream()  
				.map(itemsRequest -> {
					MenuItemsDTO menuItems = itemsRepo.findById(itemsRequest.getMenuItemsId()).orElseThrow(()-> new RuntimeException("no item found"));
					
					System.out.println("menu data: "+ menuItems.toString());
					
					OrdersItemsDTO ordersItemsDTO = new OrdersItemsDTO();
					ordersItemsDTO.setMenuItem(menuItems);
					ordersItemsDTO.setQuantity(itemsRequest.getQuantity());
					ordersItemsDTO.setSpecialInstructions(itemsRequest.getSpecialInstruction());
					ordersItemsDTO.setOrder(ordersDTO); // linked to parent class
					
					return ordersItemsDTO;
					
				}).toList();
		
		ordersDTO.setListOfOrdersItems(items);
		ordersDTO.calculateTotal();
		
		return ordersRepo.save(ordersDTO);
		
	}

}
