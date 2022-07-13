package com.capstone.kelompok10.model.dto.post;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassDtoPost {
    private Boolean status;
    private String name;
    private String description;
    private Long capacity;
    @DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate schedule;
    private Long hour;
    private Long price;
    private String imageUrl;
    private Long instructorId;
    private Long categoryId;
    private Long roomId;
    private Long typeId;
    private String videoUrl;
    private String meetUrl;
}
