package com.example.invoice.discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DiscountReposity extends JpaRepository<Discount, Integer> {
    @Query("SELECT d FROM Discount d WHERE d.discountId = :Id")
    Optional<Discount> findById(String Id);
}
