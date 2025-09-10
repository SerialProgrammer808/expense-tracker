package com.noahasano.expense.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.noahasano.expense.entity.Income;
import java.time.LocalDate;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, LocalDate startDate, LocalDate endDate);
    
    List<Income> findByUserIdOrderByDateDesc(Long userId);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.user.id = :userId")
    Double sumAllAmountsByUserId(@Param("userId") Long userId);

    Optional<Income> findFirstByUserIdOrderByDateDesc(Long userId);
    
    Optional<Income> findByIdAndUserId(Long id, Long userId);
}
