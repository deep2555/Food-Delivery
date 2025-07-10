package com.deepanshu.fooddelapi.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Menu Items")
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
	
	
	
}
