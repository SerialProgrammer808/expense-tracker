package com.noahasano.expense.services;

import org.springframework.stereotype.Service;

import com.noahasano.expense.dto.ExpenseDTO;
import com.noahasano.expense.entity.Expense;
import com.noahasano.expense.repository.ExpenseRepository;
import com.noahasano.expense.services.expense.ExpenseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImplementation implements ExpenseService{

    private final ExpenseRepository expenseRepository;
}
