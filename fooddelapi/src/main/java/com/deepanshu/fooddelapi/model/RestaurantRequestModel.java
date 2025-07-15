package com.deepanshu.fooddelapi.model;


/*here is the class only to get the request from the user and passs that request to the orignal dto class*/


public class RestaurantRequestModel {

	private String restaurantName;
	private String restaurantDescription;
    private String restaurantAddress;
    private String restaurantContactInfo;
    private String restaurantOpeningHours;
    private String restaurantCuisineType;
    private int ownerId;  // Just the ID
    
    
    
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantDescription() {
		return restaurantDescription;
	}
	public void setRestaurantDescription(String restaurantDescription) {
		this.restaurantDescription = restaurantDescription;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public String getRestaurantContactInfo() {
		return restaurantContactInfo;
	}
	public void setRestaurantContactInfo(String restaurantContactInfo) {
		this.restaurantContactInfo = restaurantContactInfo;
	}
	public String getRestaurantOpeningHours() {
		return restaurantOpeningHours;
	}
	public void setRestaurantOpeningHours(String restaurantOpeningHours) {
		this.restaurantOpeningHours = restaurantOpeningHours;
	}
	public String getRestaurantCuisineType() {
		return restaurantCuisineType;
	}
	public void setRestaurantCuisineType(String restaurantCuisineType) {
		this.restaurantCuisineType = restaurantCuisineType;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	@Override
	public String toString() {
		return "RestaurantRequestModel [restaurantName=" + restaurantName + ", restaurantDescription="
				+ restaurantDescription + ", restaurantAddress=" + restaurantAddress + ", restaurantContactInfo="
				+ restaurantContactInfo + ", restaurantOpeningHours=" + restaurantOpeningHours
				+ ", restaurantCuisineType=" + restaurantCuisineType + ", ownerId=" + ownerId + "]";
	}
    
    
}
