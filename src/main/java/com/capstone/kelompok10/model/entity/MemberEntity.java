package com.capstone.kelompok10.model.entity;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String name;
    private String period;
    private Long price;

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<MembershipEntity> membership;

    @CreationTimestamp
    private Instant created_at;

    @UpdateTimestamp
    private Instant updated_at;
}
