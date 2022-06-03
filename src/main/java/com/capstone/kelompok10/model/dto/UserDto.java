package com.capstone.kelompok10.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long user_id;
    private String name;
    private String username;
}
