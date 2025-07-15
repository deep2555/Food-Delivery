package com.deepanshu.fooddelapi.model;
	
public class MenuItemsRequestHandlerModel {

	private String itemName;
	private String itemDescription;
	private String category; // which categories the item belongs to.
    private double itemPrice;
    private int restaurantDetailId;
    
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getRestaurantDetailId() {
		return restaurantDetailId;
	}
	public void setRestaurantDetailId(int restaurantDetailId) {
		this.restaurantDetailId = restaurantDetailId;
	}
	@Override
	public String toString() {
		return "MenuItemsRequestHandlerModel [itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", category=" + category + ", itemPrice=" + itemPrice + ", restaurantDetailId=" + restaurantDetailId
				+ "]";
	}
    
	
 
    
    
    
	
}
