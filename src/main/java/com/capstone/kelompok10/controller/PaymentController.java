package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.kelompok10.model.dto.get.PaymentDtoGet;
import com.capstone.kelompok10.model.dto.post.PaymentDtoPost;
import com.capstone.kelompok10.service.interfaces.PaymentService;

@RestController
@RequestMapping("/capstone/payment")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/adminAccess/getAllPayment")
    public ResponseEntity<List<PaymentDtoGet>> findAll(){
        List<PaymentDtoGet> payment = paymentService.findAll();
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getPaymentById")
    public ResponseEntity<PaymentDtoGet> getPaymentById(@RequestParam("paymentId") Long paymentId){
        return new ResponseEntity<>(paymentService.getPaymentById(paymentId), HttpStatus.OK);
    }

    @PostMapping("/userAccess/createNewPayment")
    public String createPaymentDto(@RequestBody PaymentDtoPost paymentDtoPost){
        return paymentService.createPaymentDto(paymentDtoPost);
    }

    @GetMapping("/confirmation/confirmPayment")
    public String confirmPayment(@RequestParam("token") String token){
        return paymentService.confirmPayment(token);
    }
}