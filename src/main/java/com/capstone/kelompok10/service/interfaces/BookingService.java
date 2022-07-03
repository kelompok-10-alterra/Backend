package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capstone.kelompok10.model.dto.get.BookingDtoGetDetailed;
import com.capstone.kelompok10.model.dto.post.BookingDtoPost;
import com.capstone.kelompok10.model.entity.BookingEntity;

public interface BookingService {
    List<BookingDtoGetDetailed> findAll();
    List<BookingDtoGetDetailed> findAll(Long bookingId);
    Page<BookingEntity> findAllPagination(int offset, int pageSize);
    Page<BookingEntity> findAllPaginationSort(int offset, int pageSize, String field);
    BookingDtoGetDetailed getBookingByIdDto(Long bookingId);
    BookingEntity getBookingById(Long bookingId);
    void createBookingDto(BookingDtoPost bookingDtoPost);
    void updateBooking(Long bookingId, BookingDtoPost bookingDtoPost);
    void deleteBooking(Long bookingId);
    void deleteBookingUsingUserIdentity(Long userIdentity);
    int totalBooking();
}
