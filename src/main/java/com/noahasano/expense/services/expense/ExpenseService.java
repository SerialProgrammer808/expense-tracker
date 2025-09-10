package com.noahasano.expense.services.expense;

import java.util.List;

import com.noahasano.expense.dto.ExpenseDTO;
import com.noahasano.expense.entity.Expense;

public interface ExpenseService {
    
    Expense postExpense(ExpenseDTO expenseDTO, String username);
    List<ExpenseDTO> getAllExpenses(String username);
    ExpenseDTO getExpenseById(Long id, String username);
    Expense updateExpense(Long id, ExpenseDTO expenseDTO, String username);
    void deleteExpense(Long id, String username);
}
