package com.capstone.kelompok10.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.implementation.BookingServiceImpl;
import com.capstone.kelompok10.service.interfaces.CartService;
import com.capstone.kelompok10.service.interfaces.ClassService;
import com.capstone.kelompok10.service.interfaces.UserService;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BookingServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private BookingServiceImpl service;

    @Mock
    private ClassService classService;

    @Mock
    private UserService userService;

    @Mock
    private CartService cartService;

    @Mock
    private BookingRepository repository;

    @Mock
    private ClassRepository classRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void findAll(){
        List<BookingEntity> booking = EASY_RANDOM.objects(BookingEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(booking);
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

    @Test
    void findAllPagination(){
        List<BookingEntity> booking = EASY_RANDOM.objects(BookingEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(booking);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void findAllPaginationSort(){
        List<BookingEntity> booking = EASY_RANDOM.objects(BookingEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(booking);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getBookingByIdDto(){
        BookingEntity booking = EASY_RANDOM.nextObject(BookingEntity.class);

        when(repository.findById(booking.getBookingId())).thenReturn(Optional.of(booking));
        service.getBookingByIdDto(booking.getBookingId());
        verify(repository, times(2)).findById(booking.getBookingId());
    }

    @Test
    void deleteBooking(){
        BookingEntity booking = EASY_RANDOM.nextObject(BookingEntity.class);

        when(repository.findById(booking.getBookingId())).thenReturn(Optional.of(booking));
        service.deleteBooking(booking.getBookingId());
        verify(repository, times(1)).deleteById(booking.getBookingId());
    }

    // @Test
    // void createBookingDto(){
    //     BookingEntity bookingEntity = new BookingEntity();
    //     BookingDtoPost bookingDtoPost = new BookingDtoPost();
    //     UserEntity user2 = EASY_RANDOM.nextObject(UserEntity.class);
    //     UserEntity userEntity = new UserEntity();
    //     userEntity.setUserId(bookingDtoPost.getUserId());
    //     ClassEntity classEntity = new ClassEntity();
    //     classEntity.setClassId(bookingDtoPost.getClassId());
    //     bookingEntity.setStatus(bookingDtoPost.getStatus());
    //     bookingEntity.setClasses(classEntity);
    //     bookingEntity.setUser(userEntity);
    //     bookingEntity.setUserIdentity(bookingDtoPost.getUserId());
    //     bookingEntity.setCartIdentity(user2.getCart().getCartId());
    //     bookingEntity.setClassIdentity(bookingDtoPost.getClassId());

    //     when(repository.save(bookingEntity)).thenReturn(bookingEntity);
    //     service.createBookingDto(bookingDtoPost);
    //     verify(repository, times(2)).save(bookingEntity);
    // }

    @Test
    void totalBooking(){
        List<BookingEntity> booking = EASY_RANDOM.objects(BookingEntity.class, 3).collect(Collectors.toList());
        
        when(repository.findAll()).thenReturn(booking);
        service.totalBooking();
        verify(repository, times(1)).findAll();
        assertEquals(3, booking.size());
    }

    // @Test
    // void deleteBookingUsingUserIdentity(){
    //     List<BookingEntity> booking = EASY_RANDOM.objects(BookingEntity.class, 2).collect(Collectors.toList());
        
    //     when(repository.findAll()).thenReturn(booking);
    //     service.deleteBookingUsingUserIdentity(foreach);
    // }
}
