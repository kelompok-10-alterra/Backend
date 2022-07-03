package com.capstone.kelompok10.model.dto.get;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassDtoGet {
    private Long classId;
    private String name;
    private String description;
    private Boolean status;
    private Long capacity;
    private Long booked;
    @DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate schedule;
    private Long price;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;
    private Long typeId;
    private String typeName;
    private Long instructureId;
    private String instructureName;
    private Long contact;
    private Long categoryId;
    private String categoryName;
    private Long roomId;
    private String roomName;
}
