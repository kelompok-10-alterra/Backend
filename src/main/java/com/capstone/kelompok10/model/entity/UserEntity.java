package com.capstone.kelompok10.model.entity;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
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
    private String roleName;
    private String membership;
    private Long point = 0L;
    private Boolean status;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MembershipEntity> memberships;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BookingEntity> booking;

    @Column(nullable = true)
    private String token;

    @CreationTimestamp
    private Instant created_at;

    @UpdateTimestamp
    private Instant updated_at;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleEntity> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private CartEntity cart;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private FavoriteEntity favorite;

    // @ManyToMany(fetch = FetchType.EAGER)
    // @JsonIgnore
    // private Collection<ClassEntity> classes = new ArrayList<>();
}
