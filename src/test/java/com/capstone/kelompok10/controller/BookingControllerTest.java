// package com.capstone.kelompok10.controller;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.jeasy.random.EasyRandom;
// import org.json.JSONObject;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.RequestBuilder;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import com.capstone.kelompok10.model.dto.post.BookingDtoPost;
// import com.capstone.kelompok10.model.entity.BookingEntity;
// import com.capstone.kelompok10.repository.BookingRepository;
// import com.capstone.kelompok10.service.interfaces.BookingService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class BookingControllerTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();
    
//     @MockBean
//     private BookingService service;

//     @MockBean
//     private BookingRepository bookingRepository;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     ObjectMapper objectMapper;

//     @Test
//     @DisplayName("Get All Booking")
//     public void getAllBooking() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/booking/adminAccess/getAllBooking")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     } 

//     @Test
//     @DisplayName("Get Booking With id")
//     public void getBookingWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/booking/userAccess/getBookingById?bookingId=1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Get Booking With id Dto")
//     public void getBookingWithIdDto() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/booking/userAccess/getBookingByIdDto?bookingId=1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Post Booking Without Data")
//     public void postBookingNoData() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .post("/capstone/booking/userAccess/createNewBooking")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(400, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Post Booking With Data")
//     public void postBookingWithData() throws Exception{
//         BookingDtoPost book2 = EASY_RANDOM.nextObject(BookingDtoPost.class);

//         JSONObject payload = new JSONObject();
//         payload.put("status", book2.getStatus());
//         payload.put("userId", book2.getUserId());
//         payload.put("classId", book2.getClassId());

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .post("/capstone/booking/userAccess/createNewBooking")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(payload.toString())
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Delete Booking")
//     public void deleteBookingWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .delete("/capstone/booking/adminAccess/deleteBooking/1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(204, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Update Booking")
//     public void updateBooking() throws Exception{
//         BookingDtoPost booking = EASY_RANDOM.nextObject(BookingDtoPost.class);
//         BookingEntity booking2 = EASY_RANDOM.nextObject(BookingEntity.class);
//         booking2.setBookingId(1L);

//         JSONObject payload = new JSONObject();
//         payload.put("status", booking.getStatus());
//         payload.put("userId", booking.getUserId());
//         payload.put("classId", booking.getClassId());

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .put("/capstone/booking/adminAccess/updateBooking/1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(payload.toString())
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Count total user who Booked")
//     public void totalUser() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/booking/adminAccess/countTotalBooking")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }
// }
