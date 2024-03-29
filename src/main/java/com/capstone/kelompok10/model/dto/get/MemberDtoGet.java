package com.capstone.kelompok10.model.dto.get;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDtoGet {
    private Long memberId;
    private String name;
    private String period;
    private Long price;
    private String createdAt;
    private String updatedAt;
}
