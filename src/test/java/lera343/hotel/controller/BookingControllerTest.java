package lera343.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lera343.hotel.dto.RoomRequest;
import lera343.hotel.entity.Booking;
import lera343.hotel.repository.BookingRepository;
import lera343.hotel.repository.ClientRepository;
import lera343.hotel.repository.RoomRepository;
import lera343.hotel.repository.TypeRepository;
import lera343.hotel.stubs.BookingStub;
import lera343.hotel.stubs.BookingStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingControllerTest {

    @MockBean
    BookingRepository bookingRepository;

    @MockBean
    RoomRepository roomRepository;

    @MockBean
    ClientRepository clientRepository;

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_save() throws Exception {
        var expectedBooking = BookingStub.getRandomBooking();
        when(bookingRepository.save(any())).thenReturn(expectedBooking);
        mockMvc.perform(postRequest("/bookings", expectedBooking).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedBooking.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_save() throws Exception {
        var request = BookingStub.getRandomBooking();
        mockMvc.perform(postRequest("/bookings", request))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_save() throws Exception {
        var request = BookingStub.getRandomBooking();

        mockMvc.perform(postRequest("/bookings", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin() throws Exception {
        var expectedBooking = BookingStub.getRandomBooking();
        when(bookingRepository.findById(any())).thenReturn(Optional.of(expectedBooking));
        mockMvc.perform(getRequest("/bookings/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedBooking.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_by_id_user() throws Exception {
        var expectedBooking = BookingStub.getRandomBooking();
        when(bookingRepository.findById(any())).thenReturn(Optional.of(expectedBooking));
        mockMvc.perform(getRequest("/bookings/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedBooking.getDescription())));
    }

    @Test
    void should_return_401_get_by_id() throws Exception {
        mockMvc.perform(getRequest("/bookings/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_400_get_by_id() throws Exception {
        when(bookingRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(getRequest("/bookings/1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin() throws Exception {
        var expectedBooking = BookingStub.getRandomBooking();
        when(bookingRepository.findAll()).thenReturn(Collections.singletonList(expectedBooking));
        mockMvc.perform(getRequest("/bookings"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedBooking.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_all_user() throws Exception {
        var expectedBooking = BookingStub.getRandomBooking();
        when(bookingRepository.findAll()).thenReturn(Collections.singletonList(expectedBooking));
        mockMvc.perform(getRequest("/bookings"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedBooking.getDescription())));
    }

    @Test
    void should_return_401_get_all() throws Exception {
        mockMvc.perform(getRequest("/bookings"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_empty_array_get_all() throws Exception {
        when(bookingRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(getRequest("/bookings"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_room_admin() throws Exception {
        var expectedBooking = BookingStub.getRandomBooking();
        when(bookingRepository.findBookingsByRoom_Id(any())).thenReturn(Collections.singletonList(expectedBooking));
        mockMvc.perform(getRequest("/bookings/room/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedBooking.getDescription())))
               ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_by_room_user() throws Exception {
        var expectedBooking = BookingStub.getRandomBooking();
        when(bookingRepository.findBookingsByRoom_Id(any())).thenReturn(Collections.singletonList(expectedBooking));
        mockMvc.perform(getRequest("/bookings/room/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedBooking.getDescription())))
        ;
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_client_admin() throws Exception {
        var expectedBooking = BookingStub.getRandomBooking();
        when(bookingRepository.findBookingsByClient_Id(any())).thenReturn(Collections.singletonList(expectedBooking));
        mockMvc.perform(getRequest("/bookings/client/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedBooking.getDescription())))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_by_client_user() throws Exception {
        var expectedBooking = BookingStub.getRandomBooking();
        when(bookingRepository.findBookingsByClient_Id(any())).thenReturn(Collections.singletonList(expectedBooking));
        mockMvc.perform(getRequest("/bookings/client/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedBooking.getDescription())))
        ;
    }

    @Test
    void should_return_401_get_by_type() throws Exception {
        mockMvc.perform(getRequest("/1/bookings"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id() throws Exception {
        var request = BookingStub.getRandomBooking();
        request.setDescription("description 2");
        var expectedBooking = BookingStub.getRandomBooking();
        when(bookingRepository.findById(any())).thenReturn(Optional.of(expectedBooking));
        when(bookingRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        mockMvc.perform(putRequest("/bookings/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedBooking.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_update_by_id() throws Exception {
        var request = BookingStub.getRandomBooking();
        mockMvc.perform(putRequest("/bookings/1", request))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_update_by_id() throws Exception {
        var request = BookingStub.getRandomBooking();
        mockMvc.perform(putRequest("/bookings/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_400_update_by_id() throws Exception {
        var request = BookingStub.getRandomBooking();
        when(bookingRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(putRequest("/bookings/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/bookings/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(bookingRepository, atLeast(1)).deleteById(1L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/bookings/1"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/bookings/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


    private MockHttpServletRequestBuilder postRequest(String url, Booking request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, Booking request) {
        return put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder getRequest(String url) {
        return get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    private MockHttpServletRequestBuilder deleteRequest(String url) {
        return delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }
}