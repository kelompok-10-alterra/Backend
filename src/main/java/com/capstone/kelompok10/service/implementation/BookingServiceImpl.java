package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.get.BookingDtoGetDetailed;
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
    public List<BookingDtoGetDetailed> findAll() {
        List<BookingEntity> booking = bookingRepository.findAll();
        List<BookingDtoGetDetailed> booking2 = new ArrayList<>();
        booking.forEach(isi ->{
            BookingDtoGetDetailed dto = new BookingDtoGetDetailed();
            dto.setBookingId(isi.getBookingId());
            dto.setStatus(isi.getStatus());
            dto.setPrice(isi.getPrice());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setUserId(isi.getUser().getUserId());
            dto.setUserName(isi.getUser().getName());
            dto.setMembership(isi.getUser().getMembership());
            dto.setInstructureId(isi.getClasses().getInstructor().getInstructorId());
            dto.setInstructureName(isi.getClasses().getInstructor().getName());
            dto.setClassId(isi.getClasses().getClassId());
            dto.setClassName(isi.getClasses().getName());
            dto.setCategoryId(isi.getClasses().getCategory().getCategoryId());
            dto.setCategoryName(isi.getClasses().getCategory().getName());
            dto.setSchedule(isi.getClasses().getSchedule());
            dto.setRoom(isi.getClasses().getRoom().getName());
            dto.setType(isi.getClasses().getType().getName());

            booking2.add(dto);

        });
        return booking2;
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
    public BookingDtoGetDetailed getBookingByIdDto(Long bookingId) {
        if(bookingRepository.findById(bookingId) == null){
            log.info("Booking with id {} not found", bookingId);
            return null;
        }
        log.info("Booking with id {} found", bookingId);
        BookingEntity isi = bookingRepository.findById(bookingId).get();
        BookingDtoGetDetailed dto = new BookingDtoGetDetailed();
        dto.setBookingId(isi.getBookingId());
            dto.setStatus(isi.getStatus());
            dto.setPrice(isi.getPrice());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setUserId(isi.getUser().getUserId());
            dto.setUserName(isi.getUser().getName());
            dto.setMembership(isi.getUser().getMembership());
            dto.setInstructureId(isi.getClasses().getInstructor().getInstructorId());
            dto.setInstructureName(isi.getClasses().getInstructor().getName());
            dto.setClassId(isi.getClasses().getClassId());
            dto.setClassName(isi.getClasses().getName());
            dto.setCategoryId(isi.getClasses().getCategory().getCategoryId());
            dto.setCategoryName(isi.getClasses().getCategory().getName());
            dto.setSchedule(isi.getClasses().getSchedule());
            dto.setRoom(isi.getClasses().getRoom().getName());
            dto.setType(isi.getClasses().getType().getName());

        return dto;            
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
            booking2.setUserIdentity(bookingDtoPost.getUserId());
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
        if(bookingRepository.findById(bookingId).isPresent() == true){
            BookingEntity booking = bookingRepository.findById(bookingId).get();
            if(classService.classExist(booking.getClasses().getClassId()) == true){
                classService.unBookClass(booking.getClasses().getClassId());
                bookingRepository.deleteById(bookingId);
                log.info("Booking with id {} successfully deleted", bookingId);
            }else{
                bookingRepository.deleteById(bookingId);
                log.info("Booking with id {} successfully deleted", bookingId);
            }
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
            if (userService.userHaveMembership(bookingDtoPost.getUserId()) == 2){
                log.info("User have membership and get discount price");
                total = price - (price * 20 / 100);
                bookingEntity.setPrice(total);
            }if (userService.userHaveMembership(bookingDtoPost.getUserId()) == 3){
                log.info("User have membership and get discount price");
                total = price - (price * 30 / 100);
                bookingEntity.setPrice(total);
            }if (userService.userHaveMembership(bookingDtoPost.getUserId()) == 4){
                log.info("User have membership and get discount price");
                total = price - (price * 50 / 100);
                bookingEntity.setPrice(total);
            }else{
                log.info("User don't have membership and didn't get discount price");
                total = price;
                bookingEntity.setPrice(total);
            }
            bookingEntity.setStatus(bookingDtoPost.getStatus());
            bookingEntity.setClasses(classEntity);
            bookingEntity.setUser(userEntity);
            bookingEntity.setUserIdentity(bookingDtoPost.getUserId());
            bookingRepository.save(bookingEntity);

            classService.classBooked(bookingDtoPost.getClassId());
            userService.getPoint(bookingDtoPost.getUserId());
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

    @Override
    public void deleteBookingUsingUserIdentity(Long userIdentity) {
        List<BookingEntity> booking = bookingRepository.findAll();
        booking.forEach(isi ->{
            if(isi.getUserIdentity() == userIdentity){
                bookingRepository.delete(isi);
            }
        });
    }
}
