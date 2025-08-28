package com.noahasano.expense.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noahasano.expense.dto.GraphDTO;
import com.noahasano.expense.services.stats.StatsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@CrossOrigin("*")

public class StatsController {

    private final StatsService statsService;

    @GetMapping("/chart")
    public ResponseEntity<GraphDTO> getChartDetails() {
        return ResponseEntity.ok(statsService.getChartData());
    }
}
