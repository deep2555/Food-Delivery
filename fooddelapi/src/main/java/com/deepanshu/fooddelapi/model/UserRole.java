package com.deepanshu.fooddelapi.model;

public enum UserRole {

	
	/* a customer who can order online */
	CUSTOMER,
	
	// Restaurant owner who can manage their restaurant and menu
	RESTAURANT_OWNER,
	
	// System administrator with full access
	ADMIN;
	
	public String getAuthority() {
		return "ROLE_" + this.name();
	}
}
