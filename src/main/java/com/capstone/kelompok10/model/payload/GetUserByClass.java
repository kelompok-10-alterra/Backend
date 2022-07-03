package com.capstone.kelompok10.model.payload;

import lombok.Data;

@Data
public class GetUserByClass {
    private Long userId;
    private String username;
    private Boolean status;
    private String joinedAt;
}
