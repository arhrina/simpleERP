package com.motok.motoK.statistics.service;

import com.motok.motoK.AcceptanceTest;
import com.motok.motoK.statistics.domain.StatisticsPerDayResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class StatisticsServiceTest extends AcceptanceTest {

    @Autowired
    private StatisticsService statisticsService;

    @BeforeEach
    void dataSetup() {

    }

    @DisplayName("StatisticsService의 일별통계 조회 메소드를 테스트한다.")
    @Test
    void 일별통계_조회() {
        //given
        int month = 9;

        //when
        List<StatisticsPerDayResponse> statisticsPerDayResponses = statisticsService.getDayStatistics(month);

        //then
    }
}
