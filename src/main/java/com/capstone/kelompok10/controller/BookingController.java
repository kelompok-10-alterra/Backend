package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.kelompok10.model.dto.get.BookingDtoGet;
import com.capstone.kelompok10.model.dto.post.BookingDtoPost;
import com.capstone.kelompok10.model.entity.BookingEntity;
import com.capstone.kelompok10.service.interfaces.BookingService;

@RestController
@RequestMapping("/capstone/booking")
public class BookingController {
    BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingEntity>> findAll(){
        List<BookingEntity> booking = bookingService.findAll();
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/{offset}/{pageSize}")
    public ResponseEntity<Page<BookingEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
        Page<BookingEntity> booking = bookingService.findAllPagination(offset, pageSize);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<BookingEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        Page<BookingEntity> booking = bookingService.findAllPaginationSort(offset, pageSize, field);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<BookingDtoGet>> findAllDto(){
        List<BookingDtoGet> bookingDtos = bookingService.findAllDto();
        return new ResponseEntity<>(bookingDtos, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<BookingEntity> getBookingById(@RequestParam("bookingId") Long bookingId){
        return new ResponseEntity<>(bookingService.getBookingById(bookingId), HttpStatus.OK);
    }

    @PostMapping("/user/dto")
    public ResponseEntity<BookingDtoPost> createBookingDto(@RequestBody BookingDtoPost bookingDtoPost){
        bookingService.createBookingDto(bookingDtoPost);
        return new ResponseEntity<>(bookingDtoPost, HttpStatus.OK);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingEntity> updateBooking(@PathVariable("bookingId") Long bookingId, @RequestBody BookingDtoPost bookingDtoPost){
        bookingService.updateBooking(bookingId, bookingDtoPost);
        return new ResponseEntity<>(bookingService.getBookingById(bookingId), HttpStatus.OK);
    }

    @DeleteMapping("/user/{bookingId}")
    public ResponseEntity<BookingEntity> deleteBooking(@PathVariable("bookingId") Long bookingId){
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}