package com.capstone.kelompok10.model.dto.get;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDtoGet {
    private Long bookingId;
    private String status;
    private Long price;
    private String user;
    private String membership;
    private Long classes;
    private Date schedule;
    private String instructure;
    private String category;
    private String room;
    private String type;
}
