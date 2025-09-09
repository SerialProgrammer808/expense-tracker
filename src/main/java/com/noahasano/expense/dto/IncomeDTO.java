package com.noahasano.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class IncomeDTO {

    private Long id;

    private String title;

    private BigDecimal amount;

    private LocalDate date;

    private String category;

    private String description;
    
}
