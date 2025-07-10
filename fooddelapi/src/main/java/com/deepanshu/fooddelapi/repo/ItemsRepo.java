package com.deepanshu.fooddelapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepanshu.fooddelapi.model.MenuItemsDTO;

@Repository
public interface ItemsRepo extends JpaRepository<MenuItemsDTO, Integer>{

}
