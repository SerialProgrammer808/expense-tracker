package com.noahasano.expense.services.stats;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.noahasano.expense.dto.GraphDTO;
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
}
