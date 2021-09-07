package com.motok.motoK.service.txhistory;

import com.motok.motoK.domain.entity.txhistory.TransactionEntity;
import com.motok.motoK.domain.vo.txhistory.TxVO;
import com.motok.motoK.repository.txhistory.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TxService {

    private final TransactionRepository txRepository;

    public void saveTx(TxVO txVO) {
        TransactionEntity tx = TransactionEntity.newInstance(txVO);
        txRepository.save(tx);
    }

    public void patchTx(Long seqNo, TxVO txVO) {
        TransactionEntity tx = txRepository.findById(seqNo).orElseThrow(() -> new IllegalArgumentException("tx데이터가 없습니다."));
        tx.patchData(txVO);
    }

    public void deleteTx(Long seqNo) {
        txRepository.deleteById(seqNo);
    }
}
