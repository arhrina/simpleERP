package com.motok.motoK.account;

import com.motok.motoK.AcceptanceTest;
import com.motok.motoK.account.domain.enums.AccountCode;
import com.motok.motoK.account.dto.AccountRequest;
import com.motok.motoK.account.dto.AccountResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.motok.motoK.account.AccountTestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("가계부 관련 기능")
public class AccountAcceptanceTest extends AcceptanceTest {

    @Autowired
    private AccountRequestFactoryForTest accountRequestFactory;

    @DisplayName("특정 날짜의 가계부를 등록한다.")
    @Test
    void createAccount() {
        //given
        LocalDate date = LocalDate.now();
        AccountRequest accountRequest = accountRequestFactory.newInstance(date, 1);

        //when
        ExtractableResponse<Response> response = 가계부_등록_요청(accountRequest);

        //then
        가계부_등록됨(accountRequest, response);
    }

    @DisplayName("가계부를 수정한다.")
    @Test
    void updateAccount() {
        //given
        LocalDate date = LocalDate.now();
        AccountRequest createRequest = accountRequestFactory.newInstance(date, 1);
        ExtractableResponse<Response> createResponse = 가계부_등록되어_있음(createRequest);
        Long updateId = createResponse.jsonPath().getLong("id");
        AccountRequest updateRequest = accountRequestFactory.newInstance(date, 3);

        //when
        ExtractableResponse<Response> updateResponse = 가계부_수정_요청(updateId, updateRequest);

        //then
        가계부_수정됨(updateId, updateRequest, updateResponse);
    }

    @DisplayName("특정 날짜의 가계부를 조회한다.")
    @Test
    void getAccount() {
        //given
        //2개의 가계부내용이 이미 등록되어 있으면, 내림차순으로 2개의 json데이터가 넘어와야함.
        LocalDate date = LocalDate.now();
        AccountRequest accountRequest1 = accountRequestFactory.newInstance(date, 1);
        AccountRequest accountRequest2 = accountRequestFactory.newInstance(date, 2);
        ExtractableResponse<Response> createResponse1 = 가계부_등록되어_있음(accountRequest1);
        ExtractableResponse<Response> createResponse2 = 가계부_등록되어_있음(accountRequest2);

        //when
        ExtractableResponse<Response> getResponse = 가계부_조회_요청(date);

        //then
        가계부_조회_성공함(getResponse, Arrays.asList(createResponse2, createResponse1));
    }

    @DisplayName("가계부를 삭제한다.")
    @Test
    void deleteAccount() {
        //given
        LocalDate date = LocalDate.now();
        AccountRequest createRequest = accountRequestFactory.newInstance(date, 1);
        ExtractableResponse<Response> createResponse = 가계부_등록되어_있음(createRequest);
        Long deleteId = createResponse.jsonPath().getLong("id");

        //when
        ExtractableResponse<Response> deleteResponse = 가계부_삭제_요청(deleteId);

        //then
        가계부_삭제됨(deleteResponse);
    }

    @DisplayName("특정 날짜의 가계부 정산 금액을 조회한다.")
    @Test
    void getOnedayTotalAmount() {
        //given
        LocalDate date = LocalDate.now();
        AccountRequest accountRequest1 = accountRequestFactory.newInstance(date, 1);
        AccountRequest accountRequest2 = accountRequestFactory.newInstance(date, 2);
        ExtractableResponse<Response> createResponse1 = 가계부_등록되어_있음(accountRequest1);
        ExtractableResponse<Response> createResponse2 = 가계부_등록되어_있음(accountRequest2);

        //when
        ExtractableResponse<Response> getAmountResponse = 일일정산금액_조회_요청(date);

        //then
        일일정산금액조회_성공(getAmountResponse, Arrays.asList(createResponse1, createResponse2));
    }

    private void 일일정산금액조회_성공(ExtractableResponse<Response> getAmountResponse, List<ExtractableResponse<Response>> createResponses) {
        assertThat(getAmountResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
        int totalAmount = createResponses.stream()
                .mapToInt(r -> {
                    if (r.jsonPath().get("code").equals("PLUS"))
                        return r.jsonPath().getInt("amount");
                    else
                        return r.jsonPath().getInt("amount") * -1;
                }).sum();

        assertThat(getAmountResponse.jsonPath().getInt("totalAmount")).isEqualTo(totalAmount);
    }

    private void 가계부_등록됨(AccountRequest createRequest, ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
        assertThat(response.jsonPath().getLong("id")).isNotNull();
        assertThat(response.jsonPath().getString("date")).isEqualTo(createRequest.getDate().toString());
        assertThat(response.jsonPath().getString("content")).isEqualTo(createRequest.getContent());
        assertThat(response.jsonPath().getString("code")).isEqualTo(createRequest.getCode().toString());
        assertThat(BigInteger.valueOf(response.jsonPath().getLong("amount"))).isEqualTo(createRequest.getAmount());
        assertThat(response.jsonPath().getString("remarks")).isEqualTo(createRequest.getRemarks());
    }

    private void 가계부_수정됨(Long updateId, AccountRequest req, ExtractableResponse<Response> res) {
        assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(res.jsonPath().getLong("id")).isEqualTo(updateId);
        assertThat(res.jsonPath().getString("date")).isEqualTo(req.getDate().toString());
        assertThat(res.jsonPath().getString("content")).isEqualTo(req.getContent());
        assertThat(res.jsonPath().getString("code")).isEqualTo(req.getCode().toString());
        assertThat(BigInteger.valueOf(res.jsonPath().getLong("amount"))).isEqualTo(req.getAmount());
        assertThat(res.jsonPath().getString("remarks")).isEqualTo(req.getRemarks());
    }

    private void 가계부_조회_성공함(ExtractableResponse<Response> getResponse, List<ExtractableResponse<Response>> createResponses) {
        assertThat(getResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
        List<Long> createResIds = createResponses.stream()
                .map(r -> r.jsonPath().getLong("id"))
                .collect(Collectors.toList());
        List<Long> getResIds = getResponse.jsonPath().getList(".", AccountResponse.class).stream()
                .map(AccountResponse::getId)
                .collect(Collectors.toList());

        assertThat(getResIds).containsExactlyElementsOf(createResIds);
    }

    private void 가계부_삭제됨(ExtractableResponse<Response> deleteResponse) {
        assertThat(deleteResponse.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    private ExtractableResponse<Response> 가계부_등록되어_있음(AccountRequest accountRequest) {
        ExtractableResponse<Response> createResponse = 가계부_등록_요청(accountRequest);
        가계부_등록됨(accountRequest, createResponse);
        return createResponse;
    }
}