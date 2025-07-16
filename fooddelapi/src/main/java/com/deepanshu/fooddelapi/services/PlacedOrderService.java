package com.deepanshu.fooddelapi.services;

import com.deepanshu.fooddelapi.model.OrderRequestModelHandler;
import com.deepanshu.fooddelapi.model.OrdersDTO;

public interface PlacedOrderService {
	
	public OrdersDTO placedOrder(OrderRequestModelHandler orderRequestModelHandler);
}
