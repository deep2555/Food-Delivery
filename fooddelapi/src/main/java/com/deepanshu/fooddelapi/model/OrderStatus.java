package com.deepanshu.fooddelapi.model;

public enum OrderStatus {

	PENDING,       // Just placed, not confirmed
    CONFIRMED,     // Restaurant accepted
    PREPARING,     // Being cooked
    OUT_FOR_DELIVERY,  // On the way
    DELIVERED,     // Success!
    CANCELLED      // Cancelled by user/restaurant
}
