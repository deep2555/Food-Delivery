package com.deepanshu.fooddelapi.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepanshu.fooddelapi.model.OrderRequestModelHandler;
import com.deepanshu.fooddelapi.model.OrdersDTO;
import com.deepanshu.fooddelapi.services.PlacedOrdersServiceImpl;



@RestController
public class OrdersController {

	
	@Autowired
	private PlacedOrdersServiceImpl placedOrdersServiceImpl;
	
	// here to control all request related to the orders
	@PostMapping("/placeOrders")
	public ResponseEntity<OrdersDTO> placedOrders(@RequestBody OrderRequestModelHandler orderRequestModelHandler){
		System.out.println("inside the place order controller method");
		System.out.println("check data: "+ orderRequestModelHandler.toString());
		OrdersDTO orderResult =placedOrdersServiceImpl.placedOrder(orderRequestModelHandler);	
		return new ResponseEntity<OrdersDTO>(orderResult, HttpStatus.OK);
	}
}
