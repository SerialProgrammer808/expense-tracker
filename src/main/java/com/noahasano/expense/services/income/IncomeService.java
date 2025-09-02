package com.noahasano.expense.services.income;

import java.util.List;

import com.noahasano.expense.dto.IncomeDTO;
import com.noahasano.expense.entity.Income;

public interface IncomeService {
    Income postIncome(IncomeDTO incomeDTO);
    List<Income> getAllIncomes();
    Income getIncomeById(Long id);
    Income updateIncome(Long id, IncomeDTO incomeDTO);
    void deleteIncome(Long id);
}
