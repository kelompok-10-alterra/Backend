package com.capstone.kelompok10.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDto {
    private Long booking_id;
    private String status;
    private String user;
    private Long classes;
}
