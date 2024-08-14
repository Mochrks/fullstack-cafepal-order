package com.cafepal.order.repository;

import java.util.UUID;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafepal.order.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    List<Cart> findByUser_id(UUID userId);
}
