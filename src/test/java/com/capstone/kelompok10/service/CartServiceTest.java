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

import com.capstone.kelompok10.model.entity.CartEntity;
import com.capstone.kelompok10.repository.BookingRepository;
import com.capstone.kelompok10.repository.CartRepository;
import com.capstone.kelompok10.service.implementation.CartServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CartServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    
    @InjectMocks
    private CartServiceImpl service;

    @Mock
    private CartRepository repository;

    @Mock
    private BookingRepository bookingRepository;

    @Test
    void findAll(){
        List<CartEntity> Cart = EASY_RANDOM.objects(CartEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(Cart);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getCartById(){
        CartEntity cart = EASY_RANDOM.nextObject(CartEntity.class);

        when(repository.findById(cart.getCartId())).thenReturn(Optional.of(cart));
        service.getCartById(cart.getCartId());
        verify(repository, times(2)).findById(cart.getCartId());
    }

    // @Test
    // void addBookingToCart(){
    //     CartEntity cart = EASY_RANDOM.nextObject(CartEntity.class);
    //     BookingEntity booking = EASY_RANDOM.nextObject(BookingEntity.class);

    //     service.addBookingToCart(booking.getBookingId(), cart.getCartId());
    //     verify(repository, times(1));
    // }

    @Test
    void updateBookingTotal(){
        CartEntity cart = EASY_RANDOM.nextObject(CartEntity.class);
        Long current = cart.getTotal();
        Long price = 1000L;

        when(repository.findById(cart.getCartId())).thenReturn(Optional.of(cart));
        service.updateBookingTotal(cart.getCartId(), price);
        assertEquals(current + price, cart.getTotal());
    }

    @Test
    void unBook(){
        CartEntity cart = EASY_RANDOM.nextObject(CartEntity.class);
        Long current = cart.getTotal();
        Long newPrice = 1000L;
        Long oldPrice = 100L;

        when(repository.findById(cart.getCartId())).thenReturn(Optional.of(cart));
        service.unBook(cart.getCartId(), oldPrice, newPrice);
        assertEquals((current - oldPrice) + newPrice, cart.getTotal());
    }
}
