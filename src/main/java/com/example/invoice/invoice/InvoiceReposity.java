package com.example.invoice.invoice;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvoiceReposity extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findById(Integer Id);
    @Query("select i from Invoice i where i.user.UserId = :userId or i.consultant.UserId = :userId")
    Optional<List<Invoice>> findAllByUserId(Integer userId);
    @Modifying
    @Query(value="delete from Invoice i where i.user.UserId = :userId and i.id = :Id")
    void deleteInvoiceById(Integer Id, Integer userId);
}
