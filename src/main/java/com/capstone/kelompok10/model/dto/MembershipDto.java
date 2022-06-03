package com.capstone.kelompok10.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembershipDto {
    private Long membership_id;
    private String name;
    private Long price;
}
