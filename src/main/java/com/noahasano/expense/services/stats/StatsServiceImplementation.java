package com.noahasano.expense.services.stats;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.noahasano.expense.dto.GraphDTO;
import com.noahasano.expense.dto.StatsDTO;
import com.noahasano.expense.entity.Expense;
import com.noahasano.expense.entity.Income;
import com.noahasano.expense.entity.User;
import com.noahasano.expense.repository.ExpenseRepository;
import com.noahasano.expense.repository.IncomeRepository;
import com.noahasano.expense.services.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsServiceImplementation implements StatsService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public GraphDTO getChartData(String username) {
        User user = userService.findByUsername(username);
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(
            user.getId(), startDate, endDate));
        graphDTO.setIncomeList(incomeRepository.findByUserIdAndDateBetweenOrderByDateDesc(
            user.getId(), startDate, endDate));

        return graphDTO;
    }

    public StatsDTO getStats(String username) {
        User user = userService.findByUsername(username);
        Long userId = user.getId();
        
        Double totalIncome = incomeRepository.sumAllAmountsByUserId(userId);
        Double totalExpense = expenseRepository.sumAllAmountsByUserId(userId);

        Optional<Income> optionalIncome = incomeRepository.findFirstByUserIdOrderByDateDesc(userId);
        Optional<Expense> optionalExpense = expenseRepository.findFirstByUserIdOrderByDateDesc(userId);

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense != null ? totalExpense : 0.0);
        statsDTO.setIncome(totalIncome != null ? totalIncome : 0.0);

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        statsDTO.setBalance(statsDTO.getIncome() - statsDTO.getExpense());

        // For min/max calculations, you'd need to add repository methods
        // or fetch and calculate in memory as before, but filtered by user
        
        return statsDTO;
    }
}