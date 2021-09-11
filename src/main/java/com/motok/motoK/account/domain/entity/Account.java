package com.motok.motoK.account.domain.entity;

import com.motok.motoK.account.domain.enums.AccountCode;
import com.motok.motoK.domain.audit.AuditingAll;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Entity
public class Account extends AuditingAll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String content;
    private AccountCode code;
    private BigInteger amount;
    private String remarks;

    public Account() {
    }

    public Account(LocalDate date, String content, AccountCode code, BigInteger amount, String remarks) {
        this.date = date;
        this.content = content;
        this.code = code;
        this.amount = amount;
        this.remarks = remarks;
    }

    public void updateTo(String content, AccountCode code, BigInteger amount, String remarks) {
        this.content = content;
        this.code = code;
        this.amount = amount;
        this.remarks = remarks;
    }

}
