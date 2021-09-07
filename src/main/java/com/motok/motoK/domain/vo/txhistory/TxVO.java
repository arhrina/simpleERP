package com.motok.motoK.domain.vo.txhistory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.motok.motoK.domain.entity.txhistory.enums.TxCd;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TxVO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd")
    private LocalDate txDt;

    private String history;

    private TxCd txCd;

    private Long amount;

}
