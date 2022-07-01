package com.capstone.kelompok10.model.dto.put;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDtoPut {
    private String name;
    private String phone;
    private String address;
    private String imageUrl;
}
