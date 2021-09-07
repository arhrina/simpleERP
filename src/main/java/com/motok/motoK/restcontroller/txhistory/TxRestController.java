package com.motok.motoK.restcontroller.txhistory;

import com.motok.motoK.domain.vo.txhistory.TxVO;
import com.motok.motoK.service.txhistory.TxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tx")
@RequiredArgsConstructor
public class TxRestController {

    private final TxService txService;

    @PostMapping("")
    public String saveTx(/*@RequestBody*/ TxVO txVO) {
        txService.saveTx(txVO);
        return "성공 응답";
    }

    @PatchMapping("/{seqNo}")
    public String patchTx(@PathVariable Long seqNo, TxVO txVO) {
        txService.patchTx(seqNo, txVO);
        return "성공 응답";
    }

    @DeleteMapping("/{seqNo}")
    public String deleteTx(@PathVariable Long seqNo) {
        txService.deleteTx(seqNo);
        return "성공 응답";
    }

}