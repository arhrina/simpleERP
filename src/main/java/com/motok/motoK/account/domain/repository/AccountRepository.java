package com.motok.motoK.account.domain.repository;

import com.motok.motoK.account.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByDateOrderByIdDesc(LocalDate date);

    List<Account> findAllByDate(LocalDate date);
}
