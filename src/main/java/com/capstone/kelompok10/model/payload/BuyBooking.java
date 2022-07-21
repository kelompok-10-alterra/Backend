package com.capstone.kelompok10.model.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyBooking {
    private Long userId;
    private Long classId;
    private String method;
    private Long total;
}
