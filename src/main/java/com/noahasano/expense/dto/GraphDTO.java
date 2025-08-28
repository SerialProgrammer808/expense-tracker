package com.noahasano.expense.dto;

import java.util.List;

import com.noahasano.expense.entity.Expense;
import com.noahasano.expense.entity.Income;

import lombok.Data;

@Data
public class GraphDTO {

    private List<Expense> expenseList;

    private List<Income> incomeList;
}
