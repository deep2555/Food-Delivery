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
    
    

	public RestaurantDTO() {
		super();
	}



	public RestaurantDTO(int id, String restaurantName, String restaurantDescription, String restaurantAddress,
			String restaurantContactInfo, String restaurantOpeningHours, String restaurantCuisineType,
			UsersDTO restOwner) {
		super();
		this.id = id;
		this.restaurantName = restaurantName;
		this.restaurantDescription = restaurantDescription;
		this.restaurantAddress = restaurantAddress;
		this.restaurantContactInfo = restaurantContactInfo;
		this.restaurantOpeningHours = restaurantOpeningHours;
		this.restaurantCuisineType = restaurantCuisineType;
		this.restOwner = restOwner;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



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



	public UsersDTO getRestOwner() {
		return restOwner;
	}



	public void setRestOwner(UsersDTO restOwner) {
		this.restOwner = restOwner;
	}



	@Override
	public String toString() {
		return "RestaurantDTO [id=" + id + ", restaurantName=" + restaurantName + ", restaurantDescription="
				+ restaurantDescription + ", restaurantAddress=" + restaurantAddress + ", restaurantContactInfo="
				+ restaurantContactInfo + ", restaurantOpeningHours=" + restaurantOpeningHours
				+ ", restaurantCuisineType=" + restaurantCuisineType + ", restOwner=" + restOwner + "]";
	}
    
    
	

}
