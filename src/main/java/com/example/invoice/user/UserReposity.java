package com.example.invoice.user;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserReposity extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.UserId = :Id")
    Optional<User> findById(int Id);
    @Query("SELECT u FROM User u WHERE u.gmail = :gmail and u.isActive = TRUE")
    Optional<User> findByGmail(String gmail);
    @Query("SELECT u FROM User u order by u.isActive desc")
    Optional<List<UserReturnType>> findAlLUser();
    @Query("SELECT u FROM User u where u.isActive = TRUE")
    Optional<List<UserReturnType>> findAllActiveUser();
    @Query("SELECT u FROM User u where u.isActive = FALSE")
    Optional<List<UserReturnType>> findAllDeActiveUser();
    @Query("SELECT u FROM User u where u.isAdmin = FALSE")
    Optional<List<UserReturnType>> findAllNonAdminUser();
}
