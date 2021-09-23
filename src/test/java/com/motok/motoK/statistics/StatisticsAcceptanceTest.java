package com.motok.motoK.statistics;

import com.google.common.collect.Lists;
import com.motok.motoK.AcceptanceTest;
import com.motok.motoK.account.AccountAcceptanceTest;
import com.motok.motoK.account.AccountRequestFactoryForTest;
import com.motok.motoK.statistics.domain.StatisticsPerDayResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.motok.motoK.account.AccountTestFixture.가계부_등록_요청;
import static com.motok.motoK.statistics.StatisticsTestFixture.일별통계_조회_요청;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("통계 관련 기능")
public class StatisticsAcceptanceTest extends AcceptanceTest {

    @Autowired
    private AccountRequestFactoryForTest accountRequestFactory;

    @DisplayName("특정 월의 일일정산 내역을 마지막일부터 내림차순으로 보여준다.")
    @Test
    void 특정날짜_일별통계() {
        //given
        LocalDate day1 = LocalDate.of(2021, 8, 15);
        LocalDate day2 = LocalDate.of(2021, 9, 1);
        LocalDate day3 = LocalDate.of(2021, 9, 5);
        가계부_등록_요청(accountRequestFactory.newInstance(day1, 1));
        가계부_등록_요청(accountRequestFactory.newInstance(day2, 2));
        가계부_등록_요청(accountRequestFactory.newInstance(day3, 3));
        int month = LocalDate.of(2021, 9, 5).getMonthValue();

        //when
        ExtractableResponse<Response> response = 일별통계_조회_요청(month);

        //then
        일별통계_조회_성공함(Lists.newArrayList(day1, day2, day3), month, response);
    }

    private void 일별통계_조회_성공함(List<LocalDate> requestDays, int month, ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        Collections.sort(requestDays, Collections.reverseOrder());
        List<Integer> reqMonths = requestDays.stream()
                .filter(d -> d.getMonthValue() == month)
                .map(LocalDate::getDayOfMonth)
                .collect(Collectors.toList());
        List<Integer> resMonths = response.jsonPath().getList(".", StatisticsPerDayResponse.class).stream()
                .map(StatisticsPerDayResponse::getDay)
                .collect(Collectors.toList());
        assertThat(resMonths).containsExactlyElementsOf(reqMonths);
    }



}
