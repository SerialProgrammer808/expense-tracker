package com.noahasano.expense.services.income;

import java.util.List;

import com.noahasano.expense.dto.IncomeDTO;
import com.noahasano.expense.entity.Income;

public interface IncomeService {
    Income postIncome(IncomeDTO incomeDTO);
    List<IncomeDTO> getAllIncomes();
}
