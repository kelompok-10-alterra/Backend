package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.get.BookingDtoGet;
import com.capstone.kelompok10.model.dto.post.BookingDtoPost;
import com.capstone.kelompok10.model.entity.BookingEntity;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.BookingRepository;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.interfaces.BookingService;
import com.capstone.kelompok10.service.interfaces.ClassService;
import com.capstone.kelompok10.service.interfaces.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    BookingRepository bookingRepository;

    @Autowired
    private ClassService classService;

    @Autowired
    private UserService userService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ClassRepository classRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingEntity> findAll() {
        log.info("Get all Booking without DTO");
        List<BookingEntity> booking = new ArrayList<>();
        bookingRepository.findAll().forEach(booking::add);
        return booking;
    }
    
    @Override
    public Page<BookingEntity> findAllPagination(int offset, int pageSize) {
        log.info("Get all Booking with Pagination");
        Page<BookingEntity> booking = bookingRepository.findAll(PageRequest.of(offset, pageSize));
        return booking;
    }

    @Override
    public Page<BookingEntity> findAllPaginationSort(int offset, int pageSize, String field){
        log.info("Get all Booking with Pagination and Sorting");
        Page<BookingEntity> booking = bookingRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return booking;
    }

    @Override
    public List<BookingDtoGet> findAllDto() {
        log.info("Get all Booking with DTO");
        List<BookingEntity> bookings = bookingRepository.findAll();
        List<BookingDtoGet> bookingDtos = new ArrayList<>();
        
        bookings.forEach(isi ->{
            BookingDtoGet dto = new BookingDtoGet();
            dto.setBookingId(isi.getBookingId());
            dto.setStatus(isi.getStatus().toString());
            dto.setPrice(isi.getPrice());
            dto.setUser(isi.getUser().getName());
            dto.setMembership(isi.getUser().getMembership());
            dto.setClasses(isi.getClasses().getClassId());
            dto.setSchedule(isi.getClasses().getSchedule());
            dto.setInstructure(isi.getClasses().getInstructor().getName());
            dto.setCategory(isi.getClasses().getCategory().getName());
            dto.setRoom(isi.getClasses().getRoom().getName());
            dto.setType(isi.getClasses().getType().getName());

            bookingDtos.add(dto);
        });
        return bookingDtos;
    }

    @Override
    public BookingEntity getBookingById(Long bookingId) {
        if(bookingRepository.findById(bookingId) == null){
            log.info("Booking with id {} not found", bookingId);
            return null;
        }
        log.info("Booking with id {} found", bookingId);
        return bookingRepository.findById(bookingId).get();            
    }

    @Override
    public void updateBooking(Long bookingId, BookingDtoPost bookingDtoPost) {
        if(bookingRepository.findById(bookingId) != null){
            BookingEntity booking2 = bookingRepository.findById(bookingId).get();
        
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(bookingDtoPost.getUserId());

            ClassEntity classEntity = new ClassEntity();
            classEntity.setClassId(bookingDtoPost.getClassId());
            if(bookingDtoPost.getClassId() != booking2.getClasses().getClassId()){
                classService.unBookClass(bookingDtoPost.getClassId());
            }

            booking2.setStatus(bookingDtoPost.getStatus());
            booking2.setUser(userEntity);
            booking2.setClasses(classEntity);

            bookingRepository.save(booking2);
            log.info("Booking updated");
        }
        else{
            log.info("Booking with id {} not found", bookingId);
            throw new IllegalStateException("Booking you search not found");
        }
    }

    @Override
    public void deleteBooking(Long bookingId) {
        if(bookingRepository.findById(bookingId) != null){
            BookingEntity booking = bookingRepository.findById(bookingId).get();
            classService.unBookClass(booking.getClasses().getClassId());
            bookingRepository.deleteById(bookingId);
            log.info("Booking with id {} successfully deleted", bookingId);
        }else{
            log.info("Booking with id {} not found", bookingId);
            throw new IllegalStateException("Booking you search not found");
        }
        
    }

    @Override
    public void createBookingDto(BookingDtoPost bookingDtoPost) {
        BookingEntity bookingEntity = new BookingEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(bookingDtoPost.getUserId());
        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassId(bookingDtoPost.getClassId());

        if(classRepository.findById(bookingDtoPost.getClassId()) != null && userRepository.findById(bookingDtoPost.getUserId()) != null && classService.classFull(bookingDtoPost.getClassId()) == false){
            Long price = classService.classPrice(bookingDtoPost.getClassId());
            Long total;
            if (userService.userHaveMembership(bookingDtoPost.getUserId()) == true){
                log.info("User have membership and get discount price");
                total = price - (price * 10 / 100);
                bookingEntity.setPrice(total);
            }else{
                log.info("User don't have membership and didn't get discount price");
                total = price;
                bookingEntity.setPrice(total);
            }
            bookingEntity.setStatus(bookingDtoPost.getStatus());
            bookingEntity.setClasses(classEntity);
            bookingEntity.setUser(userEntity);
            bookingRepository.save(bookingEntity);

            classService.classBooked(bookingDtoPost.getClassId());
            log.info("Booking created");
        }else{
            log.info("Failed to create Booking");
            throw new IllegalStateException("Class / User not found or Class Full");
        }
    }

    @Override
    public int totalBooking() {
        List<BookingEntity> booking = new ArrayList<>();
        bookingRepository.findAll().forEach(booking::add);
        int sum = booking.size();
        return sum;
    }
}
