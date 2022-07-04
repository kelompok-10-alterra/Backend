package com.capstone.kelompok10.repository;

import com.capstone.kelompok10.model.entity.ClassEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    @Query("SELECT c FROM ClassEntity c WHERE c.name LIKE %?1%")
    List<ClassEntity> findAll(String keyword);
    ClassEntity findByStatus(Boolean status);
    ClassEntity findByName(String name);
    List<ClassEntity> findByTypeName(String typeName);
    List<ClassEntity> findByCategoryName(String categoryName);
}