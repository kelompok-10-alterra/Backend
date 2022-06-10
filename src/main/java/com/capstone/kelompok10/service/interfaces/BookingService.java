package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.BookingDtoGet;
import com.capstone.kelompok10.model.dto.post.BookingDtoPost;
import com.capstone.kelompok10.model.entity.BookingEntity;

public interface BookingService {
    List<BookingEntity> getAllBooking();
    List<BookingDtoGet> getAllBookingDto();
    BookingEntity getBookingById(Long booking_id);
    void createBooking(BookingEntity booking);
    void createBookingDto(BookingDtoPost bookingDtoPost);
    void updateBooking(Long booking_id, BookingEntity booking);
    void deleteBooking(Long booking_id);
}
