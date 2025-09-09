package com.noahasano.expense.dto;

import lombok.Data;

@Data
public class StatsDTO {

    private Double income;

    private Double expense;

    private IncomeDTO latestIncome;

    private ExpenseDTO latestExpense;

    private Double balance;

    private Double minIncome;

    private Double maxIncome;

    private Double minExpense;

    private Double maxExpense;
}
