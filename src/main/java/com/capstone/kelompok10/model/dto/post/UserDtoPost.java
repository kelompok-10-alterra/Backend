package com.capstone.kelompok10.model.dto.post;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String imageUrl;
    // private Long membershipId;
}
