package com.deepanshu.fooddelapi.model;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequestModelHandler {
	/* here is the dto of the 
	 * complete order like order by which user
	 *  order from which restaurant
	 *  orders contains how many items*/
	
	private int userId;
	private int restaurantId;
	private List<OrderContainItems> orderContainItems; // list of the items
	
	
	
	
	@Data
	public static class OrderContainItems{
		
		private int menuItemsId;
		private int quantity;
		private String specialInstruction;
		
		
	}
}
