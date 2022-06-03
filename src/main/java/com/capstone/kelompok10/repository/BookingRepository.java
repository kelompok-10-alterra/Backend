package com.capstone.kelompok10.repository;

import com.capstone.kelompok10.model.entity.BookingEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    
}
