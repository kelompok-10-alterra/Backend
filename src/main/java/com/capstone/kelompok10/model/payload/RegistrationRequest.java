package com.capstone.kelompok10.model.payload;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    @DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
}
