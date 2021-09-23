package com.motok.motoK.statistics.ui;

import com.motok.motoK.statistics.domain.StatisticsPerDayResponse;
import com.motok.motoK.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsRestController {
    private final StatisticsService statisticsService;

    @GetMapping("/day")
    public ResponseEntity<List<StatisticsPerDayResponse>> getDayStatistics(@RequestParam(required = false) int month) {
        if (month == 0) {
            month = LocalDate.now().getMonthValue();
        }

        return ResponseEntity.ok(new ArrayList<>());
    }

}
