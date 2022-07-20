package com.capstone.kelompok10.model.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyMembership {
    private Long userId;
    private Long memberId;
    private Long total;
    private String method;
}
