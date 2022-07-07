package com.capstone.kelompok10.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.kelompok10.model.entity.RatingEntity;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    List<RatingEntity> getRatingByUserIdentity(Long userIdentity);
    List<RatingEntity> getRatingByClassIdentity(Long classIdentity);
}
