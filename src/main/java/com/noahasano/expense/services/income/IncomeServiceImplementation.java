package com.noahasano.expense.services.income;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.noahasano.expense.dto.IncomeDTO;
import com.noahasano.expense.entity.Expense;
import com.noahasano.expense.entity.Income;
import com.noahasano.expense.repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImplementation implements IncomeService {

    private final IncomeRepository incomeRepository;

    public Income postIncome(IncomeDTO incomeDTO) {
        return saveOrUpdatIncome(new Income(), incomeDTO);
    }

    private Income saveOrUpdatIncome(Income income, IncomeDTO incomeDTO) {
        income.setTitle(incomeDTO.getTitle());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        
        return incomeRepository.save(income);
    }

    public List<IncomeDTO> getAllIncomes() {
    return incomeRepository.findAll().stream()
        .sorted(Comparator.comparing(Income::getDate).reversed())
        .map(Income::getIncomeDTO)
        .collect(Collectors.toList());
    }
}
