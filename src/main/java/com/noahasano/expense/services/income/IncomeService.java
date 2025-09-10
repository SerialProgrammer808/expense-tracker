package com.noahasano.expense.services.income;

import java.util.List;

import com.noahasano.expense.dto.IncomeDTO;
import com.noahasano.expense.entity.Income;

public interface IncomeService {
    
    Income postIncome(IncomeDTO incomeDTO, String username);
    List<IncomeDTO> getAllIncomes(String username);
    IncomeDTO getIncomeById(Long id, String username);
    Income updateIncome(Long id, IncomeDTO incomeDTO, String username);
    void deleteIncome(Long id, String username);
}
