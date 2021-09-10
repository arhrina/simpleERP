package com.motok.motoK.account.dto;

import com.motok.motoK.account.domain.entity.Account;
import com.motok.motoK.account.domain.enums.AccountCode;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
public class AccountRequest {

    public AccountRequest() {
    }

    public AccountRequest(LocalDate date, String content, AccountCode code, BigInteger amount, String remarks) {
        this.date = date;
        this.content = content;
        this.code = code;
        this.amount = amount;
        this.remarks = remarks;
    }

    private LocalDate date;
    private String content;
    private AccountCode code;
    private BigInteger amount;
    private String remarks;

    public Account toAccount() {
        return new Account(date, content, code, amount, remarks);
    }

    public void changeDate(LocalDate date) {
        this.date = date;
    }
}
