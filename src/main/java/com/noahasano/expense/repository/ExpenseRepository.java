package com.noahasano.expense.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.noahasano.expense.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, LocalDate startDate, LocalDate endDate);
    
    List<Expense> findByUserIdOrderByDateDesc(Long userId);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId")
    Double sumAllAmountsByUserId(@Param("userId") Long userId);

    Optional<Expense> findFirstByUserIdOrderByDateDesc(Long userId);
    
    Optional<Expense> findByIdAndUserId(Long id, Long userId);
}