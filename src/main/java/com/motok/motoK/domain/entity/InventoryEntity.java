package com.motok.motoK.domain.entity;

import com.motok.motoK.domain.audit.AuditingBy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InventoryEntity extends AuditingBy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seqNo;
    private String brand;
    private String partsName;
    private String innerPrice;
    private String outerPrice;
    private String serialNumber;
    private String quantity;
    // TODO 입력, 수정자?
}
