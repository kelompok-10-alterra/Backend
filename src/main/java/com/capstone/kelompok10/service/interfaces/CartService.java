package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.post.CartDtoPost;
import com.capstone.kelompok10.model.entity.CartEntity;

public interface CartService {
    List<CartEntity> findAll();
    CartEntity getCartById(Long cartId);
    void createCartDto(CartDtoPost cartDtoPost);
    void updateCart(Long cartId, CartDtoPost cartDtoPost);
    void deleteCart(Long cartId);
    void addBookingToCart(Long bookingId, Long cartId);
    void updateBookingTotal(Long cartId, Long price);
    void unBook(Long cartId, Long oldPrice, Long newPrice);
}
