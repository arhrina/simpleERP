package com.motok.motoK.customer.domain.repository;

import com.motok.motoK.customer.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
