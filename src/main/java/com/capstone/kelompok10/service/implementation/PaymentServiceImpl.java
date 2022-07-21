package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.get.PaymentDtoGet;
import com.capstone.kelompok10.model.dto.post.PaymentDtoPost;
import com.capstone.kelompok10.model.entity.BookingEntity;
import com.capstone.kelompok10.model.entity.CartEntity;
import com.capstone.kelompok10.model.entity.HistoryEntity;
import com.capstone.kelompok10.model.entity.PaymentEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.BookingRepository;
import com.capstone.kelompok10.repository.CartRepository;
import com.capstone.kelompok10.repository.HistoryRepository;
import com.capstone.kelompok10.repository.PaymentRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.email.EmailSenderService;
import com.capstone.kelompok10.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    PaymentRepository paymentRepository;

    @Autowired
    UserRepository usereRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentDtoGet> findAll() {
        List<PaymentEntity> payment = paymentRepository.findAll();
        List<PaymentDtoGet> payment2 = new ArrayList<>();
        payment.forEach(isi ->{
            PaymentDtoGet dto = new PaymentDtoGet();
            dto.setPaymentId(isi.getPaymentId());
            dto.setTotalPayment(isi.getTotalPayment());
            dto.setTotalDebt(isi.getTotalDebt());
            dto.setUsername(isi.getUsername());
            dto.setMethod(isi.getMethod());
            dto.setUserId(isi.getUser().getUserId());
            dto.setName(isi.getUser().getName());
            dto.setCartId(isi.getCart().getCartId());
            dto.setCreatedAt(isi.getCreatedAt());
            dto.setUpdatedAt(isi.getUpdatedAt());

            payment2.add(dto);

        });
        return payment2;
    }

    @Override
    public PaymentDtoGet getPaymentById(Long paymentId) {
        if(paymentRepository.findById(paymentId).isPresent()){
            PaymentEntity isi = paymentRepository.findById(paymentId).get();
            PaymentDtoGet dto = new PaymentDtoGet();
            dto.setPaymentId(isi.getPaymentId());
            dto.setTotalPayment(isi.getTotalPayment());
            dto.setTotalDebt(isi.getTotalDebt());
            dto.setUsername(isi.getUsername());
            dto.setMethod(isi.getMethod());
            dto.setUserId(isi.getUser().getUserId());
            dto.setName(isi.getUser().getName());
            dto.setCartId(isi.getCart().getCartId());
            dto.setCreatedAt(isi.getCreatedAt());
            dto.setUpdatedAt(isi.getUpdatedAt());

            return dto;
        }else{
            log.info("Payment with id {} not found", paymentId);
            return null;
        }
        
    }

    @Override
    public String createPaymentDto(PaymentDtoPost paymentDtoPost) {
        PaymentEntity payment = new PaymentEntity();
        UserEntity user = new UserEntity();
        user.setUserId(paymentDtoPost.getUserId());
        UserEntity user2 = usereRepository.findById(paymentDtoPost.getUserId()).get();
        CartEntity cart = new CartEntity();
        cart.setCartId(user2.getCart().getCartId());
        if(usereRepository.findById(paymentDtoPost.getUserId()).isPresent()){
            payment.setTotalPayment(paymentDtoPost.getTotalPayment());
            payment.setMethod(paymentDtoPost.getMethod());
            payment.setUser(user);
            payment.setCart(user2.getCart());
            String token = UUID.randomUUID().toString();
            payment.setToken(token);
            payment.setUsername(user2.getUsername());
            payment.setTotalDebt(user2.getCart().getTotal());
            paymentRepository.save(payment);

            String link = "https://www.api.rafdev.my.id/capstone/payment/adminAccess/confirmPayment?token=" + token;
            emailSenderService.sendEmail("capstone.kelompok.10@gmail.com", buildEmail(user2.getUsername(), link, paymentDtoPost.getTotalPayment() ,paymentDtoPost.getMethod(), user2.getCart().getTotal()));
            return token;
        }else{
            log.info("UserId or CartId not found");
            throw new IllegalStateException("UserId or CartId not found");
        }
        
    }

    @Override
    public String buildEmail(String username, String link, Long totalPayment, String method, Long debt) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
        "\n" +
        "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
        "\n" +
        "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
        "    <tbody><tr>\n" +
        "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
        "        \n" +
        "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
        "          <tbody><tr>\n" +
        "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
        "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
        "                  <tbody><tr>\n" +
        "                    <td style=\"padding-left:10px\">\n" +
        "                  \n" +
        "                    </td>\n" +
        "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
        "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Payment Confirmation</span>\n" +
        "                    </td>\n" +
        "                  </tr>\n" +
        "                </tbody></table>\n" +
        "              </a>\n" +
        "            </td>\n" +
        "          </tr>\n" +
        "        </tbody></table>\n" +
        "        \n" +
        "      </td>\n" +
        "    </tr>\n" +
        "  </tbody></table>\n" +
        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
        "    <tbody><tr>\n" +
        "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
        "      <td>\n" +
        "        \n" +
        "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
        "                  <tbody><tr>\n" +
        "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
        "                  </tr>\n" +
        "                </tbody></table>\n" +
        "        \n" +
        "      </td>\n" +
        "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
        "    </tr>\n" +
        "  </tbody></table>\n" +
        "\n" +
        "\n" +
        "\n" +
        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
        "    <tbody><tr>\n" +
        "      <td height=\"30\"><br></td>\n" +
        "    </tr>\n" +
        "    <tr>\n" +
        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
        "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
        "        \n" +
        "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi user with username : " + username + " Just send a Payment with total payment : " + totalPayment + " With debt : " + debt + " And with method : " + method + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Please Check the Bank Account, and if the payment is correct, please click the link below : </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Confirm Payment</a> </p></blockquote>\n CC : Capstone Kelompok 10 <p>Thank you</p>" +
        "        \n" +
        "      </td>\n" +
        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
        "    </tr>\n" +
        "    <tr>\n" +
        "      <td height=\"30\"><br></td>\n" +
        "    </tr>\n" +
        "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
        "\n" +
        "</div></div>";
    }

	@Override
	public String confirmPayment(String token) {
		if(paymentRepository.findByToken(token) != null){
            PaymentEntity payment = paymentRepository.findByToken(token);
            CartEntity cart = cartRepository.findById(payment.getCart().getCartId()).get();
            HistoryEntity history = historyRepository.findById(payment.getCart().getUser().getHistory().getHistoryId()).get();
            for (int i = 0; i < cart.getBooking().size(); i++) {
                BookingEntity booking = bookingRepository.findById(cart.getBooking().get(i).getBookingId()).get();
                booking.setStatus(true);
                bookingRepository.save(booking);
            }
            history.getBooking().addAll(cart.getBooking());
            historyRepository.save(history);
            cart.setTotal(0L);
            cart.getBooking().removeAll(cart.getBooking());
            cartRepository.save(cart);
            return "Payment Confirmed";
        }else{
            return "Payment Token not Found";
        }
	}
}
