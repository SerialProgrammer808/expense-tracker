package com.noahasano.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noahasano.expense.entity.Income;
import java.time.LocalDate;


@Repository 
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByDateBetween(LocalDate starDate, LocalDate endDate);
}
