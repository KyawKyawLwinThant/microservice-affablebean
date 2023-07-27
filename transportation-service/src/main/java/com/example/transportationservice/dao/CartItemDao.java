package com.example.transportationservice.dao;

import com.example.transportationservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemDao extends JpaRepository<CartItem,Integer> {
}
