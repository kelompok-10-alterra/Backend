package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.PaymentDtoGet;
import com.capstone.kelompok10.model.dto.post.PaymentDtoPost;

public interface PaymentService {
    List<PaymentDtoGet> findAll();
    PaymentDtoGet getPaymentById(Long PaymentId);
    String createPaymentDto(PaymentDtoPost PaymentDtoPost);
    String buildEmail(String username, String link, Long totalPayment, String method);
    String confirmPayment(String token);
}
