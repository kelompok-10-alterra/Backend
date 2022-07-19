// package com.capstone.kelompok10.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.jeasy.random.EasyRandom;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import com.capstone.kelompok10.model.dto.get.PaymentDtoGet;
// import com.capstone.kelompok10.model.entity.PaymentEntity;
// import com.capstone.kelompok10.repository.PaymentRepository;
// import com.capstone.kelompok10.service.implementation.PaymentServiceImpl;

// @ExtendWith(MockitoExtension.class)
// @ExtendWith(SpringExtension.class)
// public class PaymentServiceTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();

//     @InjectMocks
//     private PaymentServiceImpl service;

//     @Mock
//     private PaymentRepository repository;
    
//     @Test
//     void findAll(){
//         List<PaymentEntity> payment = EASY_RANDOM.objects(PaymentEntity.class, 2)
//         .collect(Collectors.toList());
//         List<PaymentDtoGet> payment2 = new ArrayList<>();
//         payment.forEach(isi ->{
//             PaymentDtoGet dto = new PaymentDtoGet();
//             dto.setPaymentId(isi.getPaymentId());
//             dto.setTotalPayment(isi.getTotalPayment());
//             dto.setTotalDebt(isi.getTotalDebt());
//             dto.setUsername(isi.getUsername());
//             dto.setMethod(isi.getMethod());
//             dto.setUserId(isi.getUser().getUserId());
//             dto.setName(isi.getUser().getName());
//             dto.setCartId(isi.getCart().getCartId());
//             dto.setCreatedAt(isi.getCreatedAt());
//             dto.setUpdatedAt(isi.getUpdatedAt());

//             payment2.add(dto);
//         });

//         when(repository.findAll()).thenReturn(payment);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getPaymentById(){
//         PaymentEntity isi = EASY_RANDOM.nextObject(PaymentEntity.class);
//         PaymentDtoGet dto = new PaymentDtoGet();
//             dto.setPaymentId(isi.getPaymentId());
//             dto.setTotalPayment(isi.getTotalPayment());
//             dto.setTotalDebt(isi.getTotalDebt());
//             dto.setUsername(isi.getUsername());
//             dto.setMethod(isi.getMethod());
//             dto.setUserId(isi.getUser().getUserId());
//             dto.setName(isi.getUser().getName());
//             dto.setCartId(isi.getCart().getCartId());
//             dto.setCreatedAt(isi.getCreatedAt());
//             dto.setUpdatedAt(isi.getUpdatedAt());

//         when(repository.findById(isi.getPaymentId())).thenReturn(Optional.of(isi));
//         service.getPaymentById(isi.getPaymentId());
//         verify(repository, times(2)).findById(isi.getPaymentId());
//     }

//     // @Test
//     // public void createPaymentDto(){
//     //     PaymentEntity paymentEntity = new PaymentEntity();
//     //     PaymentDtoPost paymentDtoPost = EASY_RANDOM.nextObject(PaymentDtoPost.class);
//     //     paymentEntity.setName(paymentDtoPost.getName());
//     //     paymentEntity.setContact(paymentDtoPost.getContact());
//     //     paymentEntity.setAddress(paymentDtoPost.getAddress());
//     //     paymentEntity.setImageUrl(paymentDtoPost.getImageUrl());

//     //     service.createPaymentDto(PaymentDtoPost);
//     //     verify(repository).save(paymentEntity);
//     // }

//     @Test
//     public void confirmPayment(){
//         PaymentEntity payment = EASY_RANDOM.nextObject(PaymentEntity.class);

//         // when(repository.findById(payment.getPaymentId())).thenReturn(Optional.of(payment));
//         service.confirmPayment(payment.getToken());
//         assertEquals("Payment Token not Found", service.confirmPayment(payment.getToken()));
//     }
// }
