package com.noahasano.expense.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.noahasano.expense.dto.ExpenseDTO;
import com.noahasano.expense.entity.Expense;
import com.noahasano.expense.repository.ExpenseRepository;
import com.noahasano.expense.services.expense.ExpenseService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImplementation implements ExpenseService{

    private final ExpenseRepository expenseRepository;

    public Expense postExpense(ExpenseDTO expenseDTO) {
        return saveOrUpdateExpense(new Expense(), expenseDTO);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO) {
        expense.setTitle(expenseDTO.getTitle());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll().stream()
            .sorted(Comparator.comparing(Expense::getDate).reversed())
            .collect(Collectors.toList());
    }

    public Expense getExpenseById(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " could not be found");
        }
    }

    public Expense updatExpense(Long id, ExpenseDTO expenseDTO) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return saveOrUpdateExpense(optionalExpense.get(), expenseDTO);
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " could not be found");
        }
    }
}
