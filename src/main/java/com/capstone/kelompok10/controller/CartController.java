package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.kelompok10.model.entity.CartEntity;
import com.capstone.kelompok10.service.interfaces.CartService;

@RestController
@RequestMapping("/capstone/cart")
public class CartController {
    CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/adminAccess/getAllCart")
        public ResponseEntity<List<CartEntity>> findAll(){
            List<CartEntity> carts = cartService.findAll();
            return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getCartById")
    public ResponseEntity<CartEntity> getRoleById(@RequestParam("cartId") Long cartId){
        cartService.updateCart(cartId);
        return new ResponseEntity<>(cartService.getCartById(cartId), HttpStatus.OK);
    }
}
