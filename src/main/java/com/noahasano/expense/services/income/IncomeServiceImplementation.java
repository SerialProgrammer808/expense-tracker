package com.noahasano.expense.services.income;

import org.springframework.stereotype.Service;

import com.noahasano.expense.dto.IncomeDTO;
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
}
