package com.capstone.kelompok10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.kelompok10.model.entity.FavoriteClassEntity;

@Repository
public interface FavoriteClassRepository extends JpaRepository<FavoriteClassEntity, Long> {
    
}
