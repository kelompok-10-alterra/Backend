package com.capstone.kelompok10.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassDto {
    private Long class_id;
    private String name;
    private String description;
    private String category;
}
