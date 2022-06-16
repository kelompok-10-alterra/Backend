package com.capstone.kelompok10.model.dto.get;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructorDtoGet {
    private Long instructor_id;
    private String name;
    private Long contact;
    private String imageUrl;
}

