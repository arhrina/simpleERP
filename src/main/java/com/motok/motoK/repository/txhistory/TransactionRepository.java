package com.motok.motoK.repository.txhistory;

import com.motok.motoK.domain.entity.txhistory.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
