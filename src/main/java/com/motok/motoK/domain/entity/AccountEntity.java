package com.motok.motoK.domain.entity;

import com.motok.motoK.domain.audit.AuditingAll;

import javax.persistence.*;

@Entity
public class AccountEntity extends AuditingAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seqNo;
    private Long 매출;
    private Long 지출;
}
