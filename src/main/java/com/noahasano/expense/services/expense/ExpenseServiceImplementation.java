package com.noahasano.expense.services.expense;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.noahasano.expense.dto.ExpenseDTO;
import com.noahasano.expense.entity.Expense;
import com.noahasano.expense.entity.User;
import com.noahasano.expense.repository.ExpenseRepository;
import com.noahasano.expense.services.user.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImplementation implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public Expense postExpense(ExpenseDTO expenseDTO, String username) {
        User user = userService.findByUsername(username);
        return saveOrUpdateExpense(new Expense(), expenseDTO, user);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO, User user) {
        expense.setTitle(expenseDTO.getTitle());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());
        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    public List<ExpenseDTO> getAllExpenses(String username) {
        User user = userService.findByUsername(username);
        return expenseRepository.findByUserIdOrderByDateDesc(user.getId()).stream()
            .map(Expense::getExpenseDTO)
            .collect(Collectors.toList());
    }

    public ExpenseDTO getExpenseById(Long id, String username) {
        User user = userService.findByUsername(username);
        Optional<Expense> optionalExpense = expenseRepository.findByIdAndUserId(id, user.getId());
        if (optionalExpense.isPresent()) {
            return optionalExpense.get().getExpenseDTO();
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " could not be found for this user");
        }
    }

    public Expense updateExpense(Long id, ExpenseDTO expenseDTO, String username) {
        User user = userService.findByUsername(username);
        Optional<Expense> optionalExpense = expenseRepository.findByIdAndUserId(id, user.getId());
        if (optionalExpense.isPresent()) {
            return saveOrUpdateExpense(optionalExpense.get(), expenseDTO, user);
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " could not be found for this user");
        }
    }

    public void deleteExpense(Long id, String username) {
        User user = userService.findByUsername(username);
        Optional<Expense> optionalExpense = expenseRepository.findByIdAndUserId(id, user.getId());
        if (optionalExpense.isPresent()) {
            expenseRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " could not be found for this user");
        }
    }
}