package com.noahasano.expense.services.income;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.noahasano.expense.dto.IncomeDTO;
import com.noahasano.expense.entity.Income;
import com.noahasano.expense.entity.User;
import com.noahasano.expense.repository.IncomeRepository;
import com.noahasano.expense.services.user.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceImplementation implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final UserService userService;

    public Income postIncome(IncomeDTO incomeDTO, String username) {
        User user = userService.findByUsername(username);
        return saveOrUpdateIncome(new Income(), incomeDTO, user);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO, User user) {
        income.setTitle(incomeDTO.getTitle());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        income.setUser(user);
        
        return incomeRepository.save(income);
    }

    public List<IncomeDTO> getAllIncomes(String username) {
        User user = userService.findByUsername(username);
        return incomeRepository.findByUserIdOrderByDateDesc(user.getId()).stream()
            .map(Income::getIncomeDTO)
            .collect(Collectors.toList());
    }

    public Income updateIncome(Long id, IncomeDTO incomeDTO, String username) {
        User user = userService.findByUsername(username);
        Optional<Income> optionalIncome = incomeRepository.findByIdAndUserId(id, user.getId());
        if (optionalIncome.isPresent()) {
            return saveOrUpdateIncome(optionalIncome.get(), incomeDTO, user);
        } else {
            throw new EntityNotFoundException("Income with id " + id + " could not be found for this user");
        }
    }

    public IncomeDTO getIncomeById(Long id, String username) {
        User user = userService.findByUsername(username);
        Optional<Income> optionalIncome = incomeRepository.findByIdAndUserId(id, user.getId());
        if (optionalIncome.isPresent()) {
            return optionalIncome.get().getIncomeDTO();
        } else {
            throw new EntityNotFoundException("Income with id " + id + " could not be found for this user");
        }
    }

    public void deleteIncome(Long id, String username) {
        User user = userService.findByUsername(username);
        Optional<Income> optionalIncome = incomeRepository.findByIdAndUserId(id, user.getId());
        if (optionalIncome.isPresent()) {
            incomeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Income with id " + id + " could not be found for this user");
        }
    }
}