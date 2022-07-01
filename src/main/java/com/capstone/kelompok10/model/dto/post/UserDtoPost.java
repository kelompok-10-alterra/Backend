package com.capstone.kelompok10.model.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDtoPost {
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String imageUrl;
    // private Long membershipId;
}
