package com.capstone.kelompok10.model.dto.get;

import java.time.Instant;

import lombok.Data;

@Data
public class PaymentDtoGet {
    private Long paymentId;
    private Long totalPayment;
    private Long totalDebt;
    private String username;
    private String method;
    private Long userId;
    private String name;
    private Long cartId;
    private Instant createdAt;
    private Instant updatedAt;
}
