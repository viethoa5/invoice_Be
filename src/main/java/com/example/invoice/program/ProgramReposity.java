package com.example.invoice.program;

import com.example.invoice.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProgramReposity extends JpaRepository<Program, Integer> {
    @Query("SELECT p FROM Program p WHERE p.programId = :Id")
    Optional<Program> findById(String Id);
}
