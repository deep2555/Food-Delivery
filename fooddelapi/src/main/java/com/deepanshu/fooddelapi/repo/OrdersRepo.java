package com.deepanshu.fooddelapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepanshu.fooddelapi.model.OrdersDTO;

@Repository
public interface OrdersRepo extends JpaRepository<OrdersDTO, Integer> {

	
}
