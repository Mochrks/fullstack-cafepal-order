package com.cafepal.order.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafepal.order.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {

}
