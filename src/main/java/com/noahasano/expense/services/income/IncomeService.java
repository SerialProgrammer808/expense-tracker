package com.noahasano.expense.services.income;

import com.noahasano.expense.dto.IncomeDTO;
import com.noahasano.expense.entity.Income;

public interface IncomeService {
    Income postIncome(IncomeDTO incomeDTO);
}
