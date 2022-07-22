package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.BookingDtoGet;
import com.capstone.kelompok10.model.dto.post.BookingDtoPost;
import com.capstone.kelompok10.model.entity.BookingEntity;
import com.capstone.kelompok10.model.payload.BookingToCart;
import com.capstone.kelompok10.model.payload.BuyBooking;

public interface BookingService {
    List<BookingDtoGet> findAll();
    List<BookingDtoGet> findAll(Long bookingId);
    // Page<BookingEntity> findAllPagination(int offset, int pageSize);
    // Page<BookingEntity> findAllPaginationSort(int offset, int pageSize, String field);
    BookingDtoGet getBookingByIdDto(Long bookingId);
    BookingEntity getBookingById(Long bookingId);
    void createBookingDto(BookingDtoPost bookingDtoPost);
    String addBookingToCart(BookingToCart bookingToCart);
    String buyClass(BuyBooking buyBooking);
    void updateBooking(Long bookingId, BookingDtoPost bookingDtoPost);
    void deleteBooking(Long bookingId);
    void deleteBookingUsingUserIdentity(Long userIdentity);
    int totalBooking();

    String buildEmail(String username, String link, Long totalPayment, String method, Long debt);
    String confirmPayment(String token);
}
