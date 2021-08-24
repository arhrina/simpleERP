package com.motok.motoK.domain.entity;

import com.motok.motoK.domain.audit.AuditingBy;

import javax.persistence.*;

@Entity
public class AccountEntity extends AuditingBy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seqNo;
    private Long 매출;
    private Long 지출;
}
