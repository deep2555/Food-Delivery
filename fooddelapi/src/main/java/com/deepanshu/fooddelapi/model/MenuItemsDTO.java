package com.deepanshu.fooddelapi.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "Menu_Items")
public class MenuItemsDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
    private String itemName;
    
    private String itemDescription;
    
    @Column(nullable = false)
    private double itemPrice;
    
    private String category;  // which categories the item belongs to 
    
    @ManyToOne
    @JoinColumn(name= "restaurant_id", nullable = false)
    private RestaurantDTO restaurant;
    
    
    
    

	public MenuItemsDTO() {
		super();
	}

	public MenuItemsDTO(int id, String itemName, String itemDescription, double itemPrice, String category,
			RestaurantDTO restaurant) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.category = category;
		this.restaurant = restaurant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public RestaurantDTO getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantDTO restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "MenuItemsDTO [id=" + id + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", itemPrice=" + itemPrice + ", category=" + category + ", restaurant=" + restaurant + "]";
	}
    
    
	
	
	
}
