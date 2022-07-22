package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.capstone.kelompok10.model.dto.get.BookingDtoGet;
import com.capstone.kelompok10.model.dto.post.BookingDtoPost;
import com.capstone.kelompok10.model.entity.BookingEntity;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.entity.HistoryEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.model.payload.BookingToCart;
import com.capstone.kelompok10.model.payload.BuyBooking;
import com.capstone.kelompok10.repository.BookingRepository;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.repository.HistoryRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.email.EmailSenderService;
import com.capstone.kelompok10.service.interfaces.BookingService;
import com.capstone.kelompok10.service.interfaces.CartService;
import com.capstone.kelompok10.service.interfaces.ClassService;
import com.capstone.kelompok10.service.interfaces.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    BookingRepository bookingRepository;

    @Autowired
    private ClassService classService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public HistoryRepository historyRepository;

    @Autowired
    public ClassRepository classRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingDtoGet> findAll() {
        List<BookingEntity> booking = bookingRepository.findAll();
        List<BookingDtoGet> booking2 = new ArrayList<>();
        booking.forEach(isi ->{
            BookingDtoGet dto = new BookingDtoGet();
            dto.setBookingId(isi.getBookingId());
            dto.setStatus(isi.getStatus());
            dto.setPrice(isi.getPrice());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setUserId(isi.getUser().getUserId());
            dto.setUserName(isi.getUser().getName());
            dto.setMembership(isi.getUser().getMembership());
            dto.setInstructureId(isi.getClasses().getInstructor().getInstructorId());
            dto.setInstructureName(isi.getClasses().getInstructor().getName());
            dto.setClassId(isi.getClasses().getClassId());
            dto.setClassIdentity(isi.getClassIdentity());
            dto.setClassName(isi.getClasses().getName());
            dto.setCategoryId(isi.getClasses().getCategory().getCategoryId());
            dto.setCategoryName(isi.getClasses().getCategory().getName());
            dto.setSchedule(isi.getClasses().getSchedule());
            dto.setRoom(isi.getClasses().getRoom().getName());
            dto.setType(isi.getClasses().getType().getName());

            booking2.add(dto);

        });
        return booking2;
    }
    
    // @Override
    // public Page<BookingEntity> findAllPagination(int offset, int pageSize) {
    //     log.info("Get all Booking with Pagination");
    //     Page<BookingEntity> booking = bookingRepository.findAll(PageRequest.of(offset, pageSize));
    //     return booking;
    // }

    // @Override
    // public Page<BookingEntity> findAllPaginationSort(int offset, int pageSize, String field){
    //     log.info("Get all Booking with Pagination and Sorting");
    //     Page<BookingEntity> booking = bookingRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    //     return booking;
    // }

    @Override
    public BookingDtoGet getBookingByIdDto(Long bookingId) {
        if(bookingRepository.findById(bookingId) == null){
            log.info("Booking with id {} not found", bookingId);
            return null;
        }
        log.info("Booking with id {} found", bookingId);
        BookingEntity isi = bookingRepository.findById(bookingId).get();
        BookingDtoGet dto = new BookingDtoGet();
        dto.setBookingId(isi.getBookingId());
            dto.setStatus(isi.getStatus());
            dto.setPrice(isi.getPrice());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setUserId(isi.getUser().getUserId());
            dto.setUserName(isi.getUser().getName());
            dto.setMembership(isi.getUser().getMembership());
            dto.setInstructureId(isi.getClasses().getInstructor().getInstructorId());
            dto.setInstructureName(isi.getClasses().getInstructor().getName());
            dto.setClassId(isi.getClasses().getClassId());
            dto.setClassName(isi.getClasses().getName());
            dto.setCategoryId(isi.getClasses().getCategory().getCategoryId());
            dto.setCategoryName(isi.getClasses().getCategory().getName());
            dto.setSchedule(isi.getClasses().getSchedule());
            dto.setRoom(isi.getClasses().getRoom().getName());
            dto.setType(isi.getClasses().getType().getName());

        return dto;            
    }

    @Override
    public BookingEntity getBookingById(Long bookingId) {
        if(bookingRepository.findById(bookingId) == null){
            log.info("Booking with id {} not found", bookingId);
            return null;
        }
        log.info("Booking with id {} found", bookingId);
        return bookingRepository.findById(bookingId).get();
    }

    @Override
    public void updateBooking(Long bookingId, BookingDtoPost bookingDtoPost) {
        if(bookingRepository.findById(bookingId) != null){
            BookingEntity booking2 = bookingRepository.findById(bookingId).get();
        
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(bookingDtoPost.getUserId());

            ClassEntity classEntity = new ClassEntity();
            classEntity.setClassId(bookingDtoPost.getClassId());

            Long price = classService.classPrice(bookingDtoPost.getClassId());
            Long total;
            Long currentPrice = booking2.getPrice();
            if(userService.nativeUser(bookingDtoPost.getUserId()) == false){
                if(bookingDtoPost.getClassId() != booking2.getClasses().getClassId()){
                    classService.unBookClass(bookingDtoPost.getClassId());
                    if (userService.userHaveMembership(bookingDtoPost.getUserId()) == 2){
                        log.info("User have membership and get discount price");
                        total = price - (price * 20 / 100);
                        booking2.setPrice(total);
                    }if (userService.userHaveMembership(bookingDtoPost.getUserId()) == 3){
                        log.info("User have membership and get discount price");
                        total = price - (price * 30 / 100);
                        booking2.setPrice(total);
                    }if (userService.userHaveMembership(bookingDtoPost.getUserId()) == 4){
                        log.info("User have membership and get discount price");
                        total = price - (price * 50 / 100);
                        booking2.setPrice(total);
                    }else{
                        log.info("User don't have membership and didn't get discount price");
                        total = price;
                        booking2.setPrice(total);
                    }
                    booking2.setPrice(total);
                    booking2.setStatus(bookingDtoPost.getStatus());
                    booking2.setUser(userEntity);
                    booking2.setUserIdentity(bookingDtoPost.getUserId());
                    booking2.setClassIdentity(bookingDtoPost.getClassId());
                    booking2.setClasses(classEntity);
                    booking2.setToken(null);
    
                    bookingRepository.save(booking2);
                    UserEntity user3 = userRepository.findById(bookingDtoPost.getUserId()).get();
                    Long cartId = user3.getCart().getCartId();
                    cartService.unBook(cartId, currentPrice, total);
                    log.info("Booking updated");
                }else{
                    if (userService.userHaveMembership(bookingDtoPost.getUserId()) == 2){
                        log.info("User have membership and get discount price");
                        total = price - (price * 20 / 100);
                        booking2.setPrice(total);
                    }if (userService.userHaveMembership(bookingDtoPost.getUserId()) == 3){
                        log.info("User have membership and get discount price");
                        total = price - (price * 30 / 100);
                        booking2.setPrice(total);
                    }if (userService.userHaveMembership(bookingDtoPost.getUserId()) == 4){
                        log.info("User have membership and get discount price");
                        total = price - (price * 50 / 100);
                        booking2.setPrice(total);
                    }else{
                        log.info("User don't have membership and didn't get discount price");
                        total = price;
                        booking2.setPrice(total);
                    }
                    booking2.setPrice(total);
                    booking2.setStatus(bookingDtoPost.getStatus());
                    booking2.setUser(userEntity);
                    booking2.setUserIdentity(bookingDtoPost.getUserId());
                    booking2.setClassIdentity(bookingDtoPost.getClassId());
                    booking2.setClasses(classEntity);
    
                    bookingRepository.save(booking2);
                    // UserEntity user3 = userRepository.findById(bookingDtoPost.getUserId()).get();
                    // Long cartId = user3.getCart().getCartId();
                    // cartService.unBook(cartId, currentPrice, total);
                    log.info("Booking updated");
                }
            }else{
                throw new IllegalStateException("Update Failed, you can't use native user");
            }
        }else{
            log.info("Booking with id {} not found", bookingId);
            throw new IllegalStateException("Booking you search not found");
        } 
    }

    @Override
    public void deleteBooking(Long bookingId) {
        if(bookingRepository.findById(bookingId).isPresent() == true){
            BookingEntity booking = bookingRepository.findById(bookingId).get();
            if(classService.classExist(booking.getClasses().getClassId()) == true){
                Long oldPrice = booking.getPrice();
                classService.unBookClass(booking.getClasses().getClassId());
                cartService.unBook(booking.getCartIdentity(), oldPrice, 0L);
                bookingRepository.deleteById(bookingId);
                log.info("Booking with id {} successfully deleted", bookingId);
            }else{
                bookingRepository.deleteById(bookingId);
                log.info("Booking with id {} successfully deleted", bookingId);
            }
        }else{
            log.info("Booking with id {} not found", bookingId);
            throw new IllegalStateException("Booking you search not found");
        }
        
    }

    @Override
    public void createBookingDto(BookingDtoPost bookingDtoPost) {
        BookingEntity bookingEntity = new BookingEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(bookingDtoPost.getUserId());
        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassId(bookingDtoPost.getClassId());

        if(classRepository.findById(bookingDtoPost.getClassId()) != null && userRepository.findById(bookingDtoPost.getUserId()) != null && classService.classFull(bookingDtoPost.getClassId()) == false
            && userService.nativeUser(bookingDtoPost.getUserId()) == false){
            UserEntity user2 = userRepository.findById(bookingDtoPost.getUserId()).get();
            Long price = classService.classPrice(bookingDtoPost.getClassId());
            Long total = price;
            bookingEntity.setPrice(total);
            if (user2.getMembership().matches("Silver")){
                log.info("User have membership and get discount price");
                total = price - (price * 20 / 100);
                bookingEntity.setPrice(total);
            }if (user2.getMembership().matches("Gold")){
                log.info("User have membership and get discount price");
                total = price - (price * 30 / 100);
                bookingEntity.setPrice(total);
            }if (user2.getMembership().matches("Platinum")){
                log.info("User have membership and get discount price");
                total = price - (price * 50 / 100);
                bookingEntity.setPrice(total);
            }
            bookingEntity.setStatus(bookingDtoPost.getStatus());
            bookingEntity.setClasses(classEntity);
            bookingEntity.setUser(userEntity);
            bookingEntity.setUserIdentity(bookingDtoPost.getUserId());
            UserEntity user3 = userRepository.findById(bookingDtoPost.getUserId()).get();
            Long cartId = user3.getCart().getCartId();
            bookingEntity.setCartIdentity(cartId);
            bookingEntity.setClassIdentity(bookingDtoPost.getClassId());
            bookingEntity.setToken(null);

            bookingRepository.save(bookingEntity);

            classService.classBooked(bookingDtoPost.getClassId());
            userService.getPoint(bookingDtoPost.getUserId());

            
            // cartService.addBookingToCart(bookingEntity.getBookingId(), cartId);
            // cartService.updateBookingTotal(cartId, total);
            log.info("Booking created");
        }else{
            log.info("Failed to create Booking");
            throw new IllegalStateException("Class / User not found or Class Full");
        }
    }

    @Override
    public int totalBooking() {
        List<BookingEntity> booking = new ArrayList<>();
        bookingRepository.findAll().forEach(booking::add);
        int sum = booking.size();
        return sum;
    }

    @Override
    public void deleteBookingUsingUserIdentity(Long userIdentity) {
        List<BookingEntity> booking = bookingRepository.findAll();
        booking.forEach(isi ->{
            if(isi.getUserIdentity() == userIdentity){
                bookingRepository.delete(isi);
            }
        });
    }

    @Override
    public List<BookingDtoGet> findAll(Long bookingId) {
        List<BookingEntity> booking = bookingRepository.findAll(bookingId);
        List<BookingDtoGet> booking2 = new ArrayList<>();
        booking.forEach(isi ->{
            BookingDtoGet dto = new BookingDtoGet();
            dto.setBookingId(isi.getBookingId());
            dto.setStatus(isi.getStatus());
            dto.setPrice(isi.getPrice());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setUserId(isi.getUser().getUserId());
            dto.setUserName(isi.getUser().getName());
            dto.setMembership(isi.getUser().getMembership());
            dto.setInstructureId(isi.getClasses().getInstructor().getInstructorId());
            dto.setInstructureName(isi.getClasses().getInstructor().getName());
            dto.setClassId(isi.getClasses().getClassId());
            dto.setClassName(isi.getClasses().getName());
            dto.setCategoryId(isi.getClasses().getCategory().getCategoryId());
            dto.setCategoryName(isi.getClasses().getCategory().getName());
            dto.setSchedule(isi.getClasses().getSchedule());
            dto.setRoom(isi.getClasses().getRoom().getName());
            dto.setType(isi.getClasses().getType().getName());

            booking2.add(dto);

        });
        return booking2;
    }

    @Override
    public String buyClass(BuyBooking buyBooking) {
        BookingEntity bookingEntity = new BookingEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(buyBooking.getUserId());
        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassId(buyBooking.getClassId());

        if(classRepository.findById(buyBooking.getClassId()) != null && userRepository.findById(buyBooking.getUserId()) != null && classService.classFull(buyBooking.getClassId()) == false
            && userService.nativeUser(buyBooking.getUserId()) == false){
            Long price = classService.classPrice(buyBooking.getClassId());
            Long total = price;
            bookingEntity.setPrice(total);
            if (userService.userHaveMembership(buyBooking.getUserId()) == 2){
                log.info("User have membership and get discount price");
                total = price - (price * 20 / 100);
                bookingEntity.setPrice(total);
            }if (userService.userHaveMembership(buyBooking.getUserId()) == 3){
                log.info("User have membership and get discount price");
                total = price - (price * 30 / 100);
                bookingEntity.setPrice(total);
            }if (userService.userHaveMembership(buyBooking.getUserId()) == 4){
                log.info("User have membership and get discount price");
                total = price - (price * 50 / 100);
                bookingEntity.setPrice(total);
            }
            bookingEntity.setStatus(false);
            bookingEntity.setClasses(classEntity);
            bookingEntity.setUser(userEntity);
            bookingEntity.setUserIdentity(buyBooking.getUserId());
            UserEntity user3 = userRepository.findById(buyBooking.getUserId()).get();
            Long cartId = user3.getCart().getCartId();
            bookingEntity.setCartIdentity(cartId);
            bookingEntity.setClassIdentity(buyBooking.getClassId());
            String token = UUID.randomUUID().toString();
            bookingEntity.setToken(token);

            UserEntity user2 = userRepository.findById(buyBooking.getUserId()).get();

            bookingRepository.save(bookingEntity);
            classService.classBooked(buyBooking.getClassId());
            String link = "https://www.api.rafdev.my.id/capstone/booking/confirmation/confirmPayment?token=" + token;
            emailSenderService.sendEmail("capstone.kelompok.10@gmail.com", buildEmail(user2.getUsername(), link, buyBooking.getTotal() ,buyBooking.getMethod(), total));
        return token;
        }else{
            return "Class / User not found or Class Full";
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
        "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi user with username : " + username + " Just send a Payment with total payment : " + totalPayment + " With total debt : " + debt + " And with method : " + method + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Please Check the Bank Account, and if the payment is correct, please click the link below : </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Confirm Payment</a> </p></blockquote>\n CC : Capstone Kelompok 10 <p>Thank you</p>" +
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
        if(bookingRepository.findByToken(token) != null){
            BookingEntity booking = bookingRepository.findByToken(token);
            booking.setStatus(true);
            bookingRepository.save(booking);
            HistoryEntity history = historyRepository.findById(booking.getUser().getHistory().getHistoryId()).get();
            history.getBooking().add(booking);
            historyRepository.save(history);
            return "Verify Success";
        }else{
            return "Verify Fail";
        }
    }

    @Override
    public String addBookingToCart(BookingToCart bookingToCart) {
        BookingEntity bookingEntity = new BookingEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(bookingToCart.getUserId());
        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassId(bookingToCart.getClassId());

        if(classRepository.findById(bookingToCart.getClassId()) != null && userRepository.findById(bookingToCart.getUserId()) != null && classService.classFull(bookingToCart.getClassId()) == false
            && userService.nativeUser(bookingToCart.getUserId()) == false){
            Long price = classService.classPrice(bookingToCart.getClassId());
            Long total;
            if (userService.userHaveMembership(bookingToCart.getUserId()) == 2){
                log.info("User have membership and get discount price");
                total = price - (price * 20 / 100);
                bookingEntity.setPrice(total);
            }if (userService.userHaveMembership(bookingToCart.getUserId()) == 3){
                log.info("User have membership and get discount price");
                total = price - (price * 30 / 100);
                bookingEntity.setPrice(total);
            }if (userService.userHaveMembership(bookingToCart.getUserId()) == 4){
                log.info("User have membership and get discount price");
                total = price - (price * 50 / 100);
                bookingEntity.setPrice(total);
            }else{
                log.info("User don't have membership and didn't get discount price");
                total = price;
                bookingEntity.setPrice(total);
            }
            bookingEntity.setStatus(false);
            bookingEntity.setClasses(classEntity);
            bookingEntity.setUser(userEntity);
            bookingEntity.setUserIdentity(bookingToCart.getUserId());
            UserEntity user3 = userRepository.findById(bookingToCart.getUserId()).get();
            Long cartId = user3.getCart().getCartId();
            bookingEntity.setCartIdentity(cartId);
            bookingEntity.setClassIdentity(bookingToCart.getClassId());
            bookingEntity.setToken(null);

            bookingRepository.save(bookingEntity);

            classService.classBooked(bookingToCart.getClassId());
            userService.getPoint(bookingToCart.getUserId());

            
            cartService.addBookingToCart(bookingEntity.getBookingId(), cartId);
            cartService.updateBookingTotal(cartId, total);
            log.info("Booking created");
            return "Booking added to cart";
        }else{
            log.info("Failed to create Booking");
            return "Class / User not found or Class Full";
        }        
    }
}
