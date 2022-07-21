package com.capstone.kelompok10.repository;

import com.capstone.kelompok10.model.entity.MembershipEntity;
import com.capstone.kelompok10.model.entity.UserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
    @Query("SELECT m FROM MembershipEntity m WHERE m.status LIKE %?1%")
    List<MembershipEntity> findAll(Boolean keyword);
    List<MembershipEntity> findByUser(UserEntity user);
    List<MembershipEntity> findByUsername(String username);
}
