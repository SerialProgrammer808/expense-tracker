package com.noahasano.expense.services.stats;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.noahasano.expense.dto.GraphDTO;
import com.noahasano.expense.dto.StatsDTO;
import com.noahasano.expense.entity.Expense;
import com.noahasano.expense.entity.Income;
import com.noahasano.expense.repository.ExpenseRepository;
import com.noahasano.expense.repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsServiceImplementation implements StatsService{

    private final IncomeRepository incomeRepository;

    private final ExpenseRepository expenseRepository;

    public GraphDTO getChartData() {
        LocalDate endDate = LocalDate.now();
        LocalDate starDate = endDate.minusDays(27);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenseRepository.findByDateBetween(starDate, endDate));
        graphDTO.setIncomeList(incomeRepository.findByDateBetween(starDate, endDate));

        return graphDTO;
    }

    public StatsDTO getStats() {
        Double totalIncome = incomeRepository.sumAllAmounts();
        Double totalExpense = expenseRepository.sumAllAmounts();

        Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        statsDTO.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        return statsDTO;
    }
}
