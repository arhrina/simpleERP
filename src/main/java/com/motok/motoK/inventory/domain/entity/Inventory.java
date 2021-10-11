package com.motok.motoK.inventory.domain.entity;

import com.motok.motoK.domain.audit.AuditingAll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventory extends AuditingAll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String partsName;
    private String innerPrice;
    private String outerPrice;
    private String serialNumber;
    private String quantity;
    // TODO 입력, 수정자?
}
