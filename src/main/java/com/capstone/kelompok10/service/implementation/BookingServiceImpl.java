package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.get.BookingDtoGet;
import com.capstone.kelompok10.model.dto.post.BookingDtoPost;
import com.capstone.kelompok10.model.entity.BookingEntity;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
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
    public List<BookingDtoGet> getAllBookingDto() {
        List<BookingEntity> bookings = bookingRepository.findAll();
        List<BookingDtoGet> bookingDtos = new ArrayList<>();
        
        bookings.forEach(isi ->{
            BookingDtoGet dto = new BookingDtoGet();
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
    public void updateBooking(Long booking_id, BookingDtoPost bookingDtoPost) {
        BookingEntity booking2 = bookingRepository.findById(booking_id).get();
        
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_id(bookingDtoPost.getUser_id());

        ClassEntity classEntity = new ClassEntity();
        classEntity.setClass_id(bookingDtoPost.getClass_id());

        booking2.setStatus(bookingDtoPost.getStatus());
        booking2.setUser(userEntity);
        booking2.setClasses(classEntity);

        bookingRepository.save(booking2);
    }

    @Override
    public void deleteBooking(Long booking_id) {
        bookingRepository.deleteById(booking_id);
    }

    @Override
    public void createBookingDto(BookingDtoPost bookingDtoPost) {
        BookingEntity bookingEntity = new BookingEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_id(bookingDtoPost.getUser_id());
        ClassEntity classEntity = new ClassEntity();
        classEntity.setClass_id(bookingDtoPost.getClass_id());

        bookingEntity.setStatus(bookingDtoPost.getStatus());
        bookingEntity.setClasses(classEntity);
        bookingEntity.setUser(userEntity);
        
        bookingRepository.save(bookingEntity);
    }
}
