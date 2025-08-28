package com.noahasano.expense.services.income;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.noahasano.expense.dto.IncomeDTO;
import com.noahasano.expense.entity.Income;
import com.noahasano.expense.repository.IncomeRepository;

import jakarta.persistence.EntityNotFoundException;
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

    public Income updateIncome(Long id, IncomeDTO incomeDTO) {
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return saveOrUpdatIncome(optionalIncome.get(), incomeDTO);
        } else {
            throw new EntityNotFoundException("Income with id " + id + " could not be found");
        }
    }

    public IncomeDTO getIncomeById(Long id) {
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return optionalIncome.get().getIncomeDTO();
        } else {
            throw new EntityNotFoundException("Income with id " + id + " could not be found");
        }
    }
}
