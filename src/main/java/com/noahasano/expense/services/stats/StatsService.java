package com.noahasano.expense.services.stats;

import com.noahasano.expense.dto.GraphDTO;
import com.noahasano.expense.dto.StatsDTO;

public interface StatsService {
    
    GraphDTO getChartData(String username);
    StatsDTO getStats(String username);
}
