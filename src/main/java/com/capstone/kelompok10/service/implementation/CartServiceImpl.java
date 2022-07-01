package com.capstone.kelompok10.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.post.CartDtoPost;
import com.capstone.kelompok10.model.entity.BookingEntity;
import com.capstone.kelompok10.model.entity.CartEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.BookingRepository;
import com.capstone.kelompok10.repository.CartRepository;
import com.capstone.kelompok10.service.interfaces.CartService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private BookingRepository bookingRepository;


    @Override
    public List<CartEntity> findAll() {
        log.info("Get all Cart with DTO");
        List<CartEntity> carts = cartRepository.findAll();
        return carts;
    }

    @Override
    public CartEntity getCartById(Long cartId) {
        if(cartRepository.findById(cartId) != null){
            log.info("Cart with id {} found", cartId);
            return cartRepository.findById(cartId).get();
        }else{
            log.info("Cart with id {} not found", cartId);
            throw new IllegalStateException("Cart not Found");
        }
    }

    @Override
    public void updateCart(Long cartId, CartDtoPost cartDtoPost) {
        if(cartRepository.findById(cartId) != null){
            CartEntity cart2 = cartRepository.findById(cartId).get();
            UserEntity user = new UserEntity();
            user.setUserId(cartDtoPost.getUserId());
            cart2.setUser(user);

            cartRepository.save(cart2);
            log.info("Cart updated");
        }else{
            log.info("Cart with id {} not found", cartId);
            throw new IllegalStateException("Cart not Found");
        }
    }

    @Override
    public void deleteCart(Long cartId) {
        if(cartRepository.findById(cartId) != null){
            cartRepository.deleteById(cartId);
            log.info("Cart with id {} successfully deleted", cartId);
        }else{
            log.info("Cart with id {} not found", cartId);
            throw new IllegalStateException("Cart not Found");
        }
    }

    @Override
    public void createCartDto(CartDtoPost cartDtoPost) {
        CartEntity cartEntity = new CartEntity();
        UserEntity user = new UserEntity();
        user.setUserId(cartDtoPost.getUserId());
        cartEntity.setUser(user);

        cartRepository.save(cartEntity);
        log.info("Cart created");
    }

	@Override
	public void addBookingToCart(Long bookingId, Long cartId) {
		CartEntity cart = cartRepository.findById(cartId).get();
        if(bookingRepository.findById(bookingId).isPresent() == true){
            BookingEntity booking = bookingRepository.findById(bookingId).get();
            cart.getBooking().add(booking);
            cartRepository.save(cart);
        }else{
            log.info("Booking with id {} no exist !!", bookingId);
        }
		
	}

    @Override
    public void updateBookingTotal(Long cartId, Long price) {
        CartEntity cart = cartRepository.findById(cartId).get();
        Long currentPrice = cart.getTotal();
        cart.setTotal(currentPrice + price);
        cartRepository.save(cart);   
    }

    @Override
    public void unBook(Long cartId, Long oldPrice, Long newPrice) {
        CartEntity cart = cartRepository.findById(cartId).get();
        Long currentPrice = cart.getTotal();
        cart.setTotal((currentPrice - oldPrice) + newPrice);
        cartRepository.save(cart);
    }
}
