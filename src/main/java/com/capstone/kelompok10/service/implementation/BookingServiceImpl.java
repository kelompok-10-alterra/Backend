package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.BookingDto;
import com.capstone.kelompok10.model.entity.BookingEntity;
import com.capstone.kelompok10.repository.BookingRepository;
import com.capstone.kelompok10.service.interfaces.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingEntity> getAllBooking() {
        List<BookingEntity> bookings = new ArrayList<>();
        bookingRepository.findAll().forEach(bookings::add);
        return bookings;
    }

    @Override
    public List<BookingDto> getAllBookingDto() {
        List<BookingEntity> bookings = bookingRepository.findAll();
        List<BookingDto> bookingDtos = new ArrayList<>();
        
        bookings.forEach(isi ->{
            BookingDto dto = new BookingDto();
            dto.setBooking_id(isi.getBooking_id());
            dto.setStatus(isi.getStatus().toString());
            dto.setUser(isi.getUser().getName());
            dto.setClasses(isi.getClasses().getClass_id());

            bookingDtos.add(dto);
        });
        return bookingDtos;
    }

    @Override
    public BookingEntity getBookingById(Long booking_id) {
        return bookingRepository.findById(booking_id).get();
    }

    @Override
    public void createBooking(BookingEntity booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void updateBooking(Long booking_id, BookingEntity booking) {
        BookingEntity booking2 = bookingRepository.findById(booking_id).get();
        System.out.println(booking2.toString());
        booking2.setStatus(booking.getStatus());
        booking2.setUser(booking.getUser());

        bookingRepository.save(booking2);
    }

    @Override
    public void deleteBooking(Long booking_id) {
        bookingRepository.deleteById(booking_id);
    }
}
