package com.capstone.kelompok10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.kelompok10.model.entity.ContentEntity;

@Repository
public interface ContentRepository extends JpaRepository<ContentEntity, Long> {
    ContentEntity findByTitle(String title);
}
