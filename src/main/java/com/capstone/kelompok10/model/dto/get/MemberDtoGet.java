package com.capstone.kelompok10.model.dto.get;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDtoGet {
    private Long member_id;
    private String length;
    private Long price;
}