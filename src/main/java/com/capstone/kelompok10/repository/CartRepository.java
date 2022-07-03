package com.capstone.kelompok10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.kelompok10.model.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>{
    
}
