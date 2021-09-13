package com.motok.motoK.domain.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CustomerVO {
    private Long 주행거리;
    private String 기종;
    private String 차량정보;
    private String 고객이름;
    private String 고객연락처;
    private String 비고;
    private String 정비내역;
    private String 작업자;
}
