package com.motok.motoK.statistics.domain;

import lombok.Getter;

@Getter
public class StatisticsPerDayResponse extends StatisticsResponse {
    private int month;
    private int day;
}
