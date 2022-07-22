package com.capstone.kelompok10.model.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingToCart {
    private Long userId;
    private Long classId;
}
