package com.capstone.kelompok10.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capstone.kelompok10.model.entity.BookingEntity;
import com.capstone.kelompok10.repository.BookingRepository;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.service.implementation.BookingServiceImpl;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BookingServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private BookingServiceImpl service;

    @Mock
    private BookingRepository repository;

    @Mock
    private ClassRepository classRepository;

    @Test
    void findAll(){
        List<BookingEntity> addresss = EASY_RANDOM.objects(BookingEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(addresss);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getBookingById(){
        BookingEntity booking = EASY_RANDOM.nextObject(BookingEntity.class);

        when(repository.findById(booking.getBookingId())).thenReturn(Optional.of(booking));
        service.getBookingById(booking.getBookingId());
        verify(repository, times(2)).findById(booking.getBookingId());
    }

    // @Test
    // void createBookingDto(){
    //     ClassEntity class2 = EASY_RANDOM.nextObject(ClassEntity.class);
    //     UserEntity user2 = EASY_RANDOM.nextObject(UserEntity.class);
    //     BookingEntity booking = new BookingEntity(1L, true, 50000L, null, null, user2, class2);
    //     BookingDtoPost bookingDtoPost = new BookingDtoPost();

    //     when(repository.save(booking)).thenReturn(booking);
    //     service.createBookingDto(bookingDtoPost);
    //     verify(repository, times(1)).save(booking);
    // }

    // @Test
    // public void deleteAddressById(){
    //     BookingEntity booking = new BookingEntity(1L, true, 50000L, null, null, null, null);

    //     when(repository.findById(booking.getBookingId())).thenReturn(Optional.of(booking));
    //     service.deleteBooking(booking.getBookingId());
    //     verify(repository).deleteById(booking.getBookingId());
    // }
}
