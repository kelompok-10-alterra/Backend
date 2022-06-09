package com.capstone.kelompok10.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassDto {
    private Long class_id;
    private Long capacity;
    private String schedule;
    private Long price;
    private String status;
    private String type;
    private String instructor;
    private String category;
    private String room;
}
