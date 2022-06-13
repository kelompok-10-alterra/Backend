package com.capstone.kelompok10.model.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembershipDtoPost {
    private Boolean status;
    private Long user_id;
    private Long member_id;
}
