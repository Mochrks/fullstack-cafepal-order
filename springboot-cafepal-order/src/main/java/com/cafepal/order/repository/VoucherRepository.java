package com.cafepal.order.repository;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafepal.order.model.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, UUID> {
    Optional<Voucher> findByCode(String code);

    Optional<Voucher> findByCodeAndIsActive(String code, Boolean isActive);
}
