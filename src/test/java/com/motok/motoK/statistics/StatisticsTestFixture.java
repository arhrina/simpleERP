package com.motok.motoK.statistics;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class StatisticsTestFixture {
    public static ExtractableResponse<Response> 일별통계_조회_요청(int month) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("month", month)
                .when()
                .get("/statistics/day")
                .then().log().all()
                .extract();
    }
}
