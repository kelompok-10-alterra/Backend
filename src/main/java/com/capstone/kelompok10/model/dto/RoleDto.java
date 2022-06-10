package com.capstone.kelompok10.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto {
    private Long role_id;
    private String name;
    private String description;
}