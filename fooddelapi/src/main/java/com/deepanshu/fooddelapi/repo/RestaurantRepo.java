package com.deepanshu.fooddelapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepanshu.fooddelapi.model.RestaurantDTO;

@Repository
public interface RestaurantRepo extends JpaRepository<RestaurantDTO, Integer>{

}
