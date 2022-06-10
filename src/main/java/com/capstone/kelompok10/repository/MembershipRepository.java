package com.capstone.kelompok10.repository;

import com.capstone.kelompok10.model.entity.MembershipEntity;
import com.capstone.kelompok10.model.payload.StatusEnum;
import com.capstone.kelompok10.model.payload.TypeEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
    MembershipEntity findByStatus(StatusEnum status);
    MembershipEntity findByType(TypeEnum type);
}
