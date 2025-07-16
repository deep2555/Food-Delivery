package com.deepanshu.fooddelapi.model;



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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_Contain_order")
public class OrdersItemsDTO {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrdersDTO order;  // Parent order

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItemsDTO menuItem;  // Which item was ordered

    private Integer quantity;  // How many?
    private String specialInstructions;  // Custom requests (e.g., "No onions")
}
