package com.capstone.kelompok10.model.dto.get;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembershipDtoGet {
    private Long membershipId;
    private Boolean status;
    private String createdAt;
    private String updatedAt;
    private Long userId;
    private String username;
    private String email;
    private String name;
    private Long contact;
    private Long memberId;
    private String member;
    private LocalDateTime expiredAt;
}
