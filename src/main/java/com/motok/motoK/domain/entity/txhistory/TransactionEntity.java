package com.motok.motoK.domain.entity.txhistory;

import com.motok.motoK.domain.audit.AuditingAll;
import com.motok.motoK.domain.entity.txhistory.enums.TxCd;
import com.motok.motoK.domain.vo.txhistory.TxVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor
public class TransactionEntity extends AuditingAll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seqNo;

    private LocalDate txDt;

    private String history;

    private TxCd txCd;

    private long amount;

    public static TransactionEntity newInstance(TxVO txVO) {
        return TransactionEntity.builder()
                .txDt(txVO.getTxDt())
                .history(txVO.getHistory())
                .txCd(txVO.getTxCd())
                .amount(txVO.getAmount())
                .build();
    }

    public void patchData(TxVO txVO) {
        history = txVO.getHistory();
        txCd = txVO.getTxCd();
        amount = txVO.getAmount();
    }
}
