package com.capstone.kelompok10.model.dto.get;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDtoGet {
    private Long bookingId;
    private Boolean status;
    private Long price;
    private String createdAt;
    private String updatedAt;
    private Long userId;
    private String userName;
    private String membership;
    private Long instructureId;
    private String instructureName;
    private Long classId;
    private Long classIdentity;
    private String className;
    private Long categoryId;
    private String categoryName;
    @DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate schedule;
    private String room;
    private String type;
}
