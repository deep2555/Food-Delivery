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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Restaurants")
public class RestaurantDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	 @Column(nullable = false)
	private String restaurantName;
	private String restaurantDescription;
    private String restaurantAddress;
    private String restaurantContactInfo;
    private String restaurantOpeningHours;
    private String restaurantCuisineType;

    @ManyToOne
    @JoinColumn(name = "Owner_Id")
    private UsersDTO restOwner;
	

}
