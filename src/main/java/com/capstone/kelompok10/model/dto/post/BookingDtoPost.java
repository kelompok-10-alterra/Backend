package com.capstone.kelompok10.model.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDtoPost {
    private Boolean status;
    private Long user_id;
    private Long class_id;
}
