package com.capstone.kelompok10.model.dto.get;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembershipDtoGet {
    private Long membership_id;
    private Boolean status;
    private String user;
    private String member;
}
