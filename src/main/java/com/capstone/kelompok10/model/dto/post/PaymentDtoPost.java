package com.capstone.kelompok10.model.dto.post;

import lombok.Data;

@Data
public class PaymentDtoPost {
    private Long totalPayment;
    private String method;
    private Long userId;
}
