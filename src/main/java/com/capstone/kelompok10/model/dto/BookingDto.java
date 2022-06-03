package com.capstone.kelompok10.model.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDto {
    private Long booking_id;
    private String name;
    private Date date;
}
