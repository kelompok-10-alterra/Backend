package com.capstone.kelompok10.repository;

import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.payload.StatusEnum;
import com.capstone.kelompok10.model.payload.TypeEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    ClassEntity findByStatus(StatusEnum status);
    ClassEntity findByType(TypeEnum type);
}