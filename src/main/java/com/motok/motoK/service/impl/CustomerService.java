package com.motok.motoK.service.impl;

import com.motok.motoK.domain.entity.CustomerEntity;
import com.motok.motoK.domain.vo.CustomerVO;
import com.motok.motoK.repository.CustomerRepository;
import com.motok.motoK.service.AbCustomerService;
import com.motok.motoK.utils.ClassUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService extends AbCustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public int insert(CustomerVO vo) {
        CustomerEntity customerEntity = ClassUtils.getModelMapper().map(vo, CustomerEntity.class);
        if(customerRepository.save(customerEntity) != null) {
            return 1;
        }
        else
            return 0;
    }
}
