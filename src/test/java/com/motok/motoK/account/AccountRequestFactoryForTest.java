package com.motok.motoK.account;

import com.motok.motoK.account.domain.enums.AccountCode;
import com.motok.motoK.account.dto.AccountRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccountRequestFactoryForTest implements InitializingBean {
    private Map<Integer, AccountRequest> accountRequestMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        accountRequestMap.put(1, new AccountRequest(LocalDate.now(), "시프트 판매", AccountCode.PLUS, BigInteger.valueOf(20000), "비고 없음"));
        accountRequestMap.put(2, new AccountRequest(LocalDate.now(), "휠 구매", AccountCode.MINUS, BigInteger.valueOf(10000), "많이 필요"));
        accountRequestMap.put(3, new AccountRequest(LocalDate.now(), "시프트 2개 판매", AccountCode.PLUS, BigInteger.valueOf(40000), "비고 많음"));
    }

    public AccountRequest newInstance(LocalDate date, int type) {
        if (accountRequestMap.containsKey(type)) {
            AccountRequest accountRequest = accountRequestMap.get(type);
            accountRequest.changeDate(date);
            return accountRequest;
        }
        throw new IllegalArgumentException("존재하지 않는 타입입니다.");
    }
}