package com.capstone.kelompok10.model.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InstructorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructor_id;
    private String name;
    private String phone;
    @CreationTimestamp
    private Instant created_at;
    @UpdateTimestamp
    private Instant updated_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
