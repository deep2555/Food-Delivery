package com.deepanshu.fooddelapi.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class OrdersDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UsersDTO userDto; // who placed the order

	@ManyToOne
	@JoinColumn(name = "restaurant_id", nullable = false)
	private RestaurantDTO restaurantDTO; // from which restaurant

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrdersItemsDTO> listOfOrdersItems; // list of items contain 1 orders

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus = OrderStatus.PENDING; // set the by default status

	private LocalDateTime orderTime = LocalDateTime.now(); // Auto-set on creation
	private Double totalPrice; // Calculated automatically

	// this is the helper method which calculate the total price of the orders
	public void calculateTotal() {
		// add the functionality soon
		this.totalPrice = listOfOrdersItems.stream()
				.mapToDouble(items -> items.getMenuItem().getItemPrice() * items.getQuantity()).sum(); // check this method later 
	}
}
