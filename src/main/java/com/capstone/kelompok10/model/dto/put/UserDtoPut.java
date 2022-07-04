package com.capstone.kelompok10.model.dto.put;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDtoPut {
    private String name;
    private String phone;
    private String address;
    private String imageUrl;
    @DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
}
