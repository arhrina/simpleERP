package com.motok.motoK.account.dto;

import com.motok.motoK.account.domain.entity.Account;
import com.motok.motoK.account.domain.enums.AccountCode;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
public class AccountResponse {
    private Long id;
    private LocalDate date;
    private String content;
    private AccountCode code;
    private BigInteger amount;
    private String remarks;

    public static AccountResponse of(Account account) {
        return new AccountResponse(account.getId(), account.getDate(), account.getContent(), account.getCode(), account.getAmount(), account.getRemarks());
    }

    public AccountResponse(Long id, LocalDate date, String content, AccountCode code, BigInteger amount, String remarks) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.code = code;
        this.amount = amount;
        this.remarks = remarks;
    }
}
