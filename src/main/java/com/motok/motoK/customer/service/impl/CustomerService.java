package com.motok.motoK.customer.service.impl;

import com.motok.motoK.customer.domain.repository.CustomerRepository;
import com.motok.motoK.customer.service.AbCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService extends AbCustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void 신규고객등록() {

    }

    @Override
    public void 기존고객수정() {

    }

    @Override
    public void 고객삭제() {

    }

    @Override
    public void 고객정보추가() {

    }
}
