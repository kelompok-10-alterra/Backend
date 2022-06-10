package com.capstone.kelompok10.model.dto.post;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassDtoPost {
    private Boolean status;
    private Long capacity;
    private Date schedule;
    private Long price;
    private Long instructor_id;
    private Long category_id;
    private Long room_id;
    private Long type_id;
}
