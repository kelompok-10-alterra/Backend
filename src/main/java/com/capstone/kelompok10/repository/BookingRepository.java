package com.capstone.kelompok10.repository;

import com.capstone.kelompok10.model.entity.BookingEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    @Query("SELECT b FROM BookingEntity b WHERE b.bookingId LIKE ?1")
    List<BookingEntity> findAll(Long bookingId);
    Optional<BookingEntity> findById(Long bookingId);
    BookingEntity findByStatus(Boolean status);
    Page<BookingEntity> findAll(Pageable pageable);
    List<BookingEntity> findByUserIdentity(Long userIdentity);
}
