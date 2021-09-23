package com.motok.motoK.account;

import com.motok.motoK.account.dto.AccountRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.time.LocalDate;

public class AccountTestFixture {
    public static ExtractableResponse<Response> 일일정산금액_조회_요청(LocalDate date) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("date", date.toString())
                .when()
                .get("/account/amount")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 가계부_등록_요청(AccountRequest accountRequest1) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(accountRequest1)
                .when()
                .post("/account")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 가계부_수정_요청(Long updateId, AccountRequest updateRequest) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(updateRequest)
                .when()
                .patch("/account/" + updateId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 가계부_조회_요청(LocalDate date) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("date", date.toString())
                .when()
                .get("/account")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 가계부_삭제_요청(Long deleteId) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/account/" + deleteId)
                .then().log().all()
                .extract();
    }
}
