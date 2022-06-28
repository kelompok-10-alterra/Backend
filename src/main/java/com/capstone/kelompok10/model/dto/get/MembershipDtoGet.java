package com.capstone.kelompok10.model.dto.get;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembershipDtoGet {
    private Long membershipId;
    private Boolean status;
    private String user;
    private String member;
    private LocalDateTime expiredAt;
}
