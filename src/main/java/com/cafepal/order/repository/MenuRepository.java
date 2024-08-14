package com.cafepal.order.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafepal.order.model.MenuItem;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, UUID> {

}
