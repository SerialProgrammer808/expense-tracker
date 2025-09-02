package com.noahasano.expense.services.expense;

import java.util.List;

import com.noahasano.expense.dto.ExpenseDTO;
import com.noahasano.expense.entity.Expense;

public interface ExpenseService {
    Expense postExpense(ExpenseDTO expenseDTO);
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long id);
    Expense updateExpense(Long id, ExpenseDTO expenseDTO);
    void deleteExpense(Long id);
}
