package com.motok.motoK.customer.domain.entity;

import com.motok.motoK.domain.audit.AuditingAll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer extends AuditingAll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long 주행거리;
    private String 기종;
    private String 차량정보;
    private String 고객이름;
    private String 고객연락처;
    private String 비고;
    private String 정비내역;
    private String 작업자;
    // TODO 생성일, 최종 업데이트일
}
