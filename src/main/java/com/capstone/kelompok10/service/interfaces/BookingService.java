package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.BookingDto;
import com.capstone.kelompok10.model.entity.BookingEntity;

public interface BookingService {
    List<BookingEntity> getAllBooking();
    List<BookingDto> getAllBookingDto();
    BookingEntity getBookingById(Long booking_id);
    void createBooking(BookingEntity booking);
    void updateBooking(Long booking_id, BookingEntity booking);
    void deleteBooking(Long booking_id);
}
