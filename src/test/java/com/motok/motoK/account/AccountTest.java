package com.motok.motoK.account;

import com.motok.motoK.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("가계부 관련 기능")
public class AccountTest extends AcceptanceTest {

    @DisplayName("특정 날짜의 가계부를 조회한다.")
    @Test
    void getTodayAccount() {
        //given
        //2개의 가계부내용이 이미 등록되어 있으면, 내림차순으로 2개의 json데이터가 넘어와야함.

        //when
        //get요청으로 조회
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/account")
                .then().log().all()
                .extract();

        //then
        //응답은 OK. 내용에 2개의 데이터가 내림차순으로 정렬.
        //안의 데이터는 given에서 적은 2개의 데이터와 response의 데이터가 맞는지 확인
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

    }

    @DisplayName("특정 날짜의 가계부를 등록한다.")
    @Test
    void createAccount() {
        //지난 날짜의 가계부를 등록할 수 있는가?
        //given
        //날짜, 내역, 금액, 비고, +/- 파라미터 전송.

        //when
        //post요청으로 insert

        //then
        //response http status code 201 ,
    }

    @DisplayName("가계부를 수정한다.")
    @Test
    void updateAccount() {
        //지난 날짜 가계부 수정 가능.
        //given
        //id, 내역, 금액, 비고, +/- 파라미터 전송.

        //when
        //update 요청으로 update, 바뀐 값 응답으로 전송

        //then
        //ok, 바뀐 값 given이랑 확인
    }
}
