package lera343.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lera343.hotel.dto.RoomRequest;
import lera343.hotel.repository.RoomRepository;
import lera343.hotel.repository.TypeRepository;
import lera343.hotel.stubs.RoomStub;
import lera343.hotel.stubs.TypeStub;
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

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoomControllerTest {

    @MockBean
    RoomRepository roomRepository;

    @MockBean
    TypeRepository typeRepository;

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
        var request = RoomStub.getRoomRequest();
        var expectedRoom = RoomStub.getRandomRoom();
        when(roomRepository.save(any())).thenReturn(expectedRoom);
        mockMvc.perform(postRequest("/rooms", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedRoom.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedRoom.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_save() throws Exception {
        var request = RoomStub.getRoomRequest();
        mockMvc.perform(postRequest("/rooms", request))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_save() throws Exception {
        var request = RoomStub.getRoomRequest();

        mockMvc.perform(postRequest("/rooms", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin() throws Exception {
        var expectedType = RoomStub.getRandomRoom();
        when(roomRepository.findById(any())).thenReturn(Optional.of(expectedType));
        mockMvc.perform(getRequest("/rooms/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_by_id_user() throws Exception {
        var expectedType = RoomStub.getRandomRoom();
        when(roomRepository.findById(any())).thenReturn(Optional.of(expectedType));
        mockMvc.perform(getRequest("/rooms/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    void should_return_401_get_by_id() throws Exception {
        mockMvc.perform(getRequest("/rooms/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_400_get_by_id() throws Exception {
        when(roomRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(getRequest("/rooms/1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin() throws Exception {
        var expectedType = RoomStub.getRandomRoom();
        when(roomRepository.findAll()).thenReturn(Collections.singletonList(expectedType));
        mockMvc.perform(getRequest("/rooms"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_all_user() throws Exception {
        var expectedType = RoomStub.getRandomRoom();
        when(roomRepository.findAll()).thenReturn(Collections.singletonList(expectedType));
        mockMvc.perform(getRequest("/rooms"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    void should_return_401_get_all() throws Exception {
        mockMvc.perform(getRequest("/rooms"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_empty_array_get_all() throws Exception {
        when(roomRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(getRequest("/rooms"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_type_admin() throws Exception {
        var expectedRoom = RoomStub.getRandomRoom();
        when(roomRepository.findRoomsByType_Id(any())).thenReturn(Collections.singletonList(expectedRoom));
        mockMvc.perform(getRequest("/rooms/type/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedRoom.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedRoom.getDescription())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedRoom.getPrice().toString())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_by_type_user() throws Exception {
        var expectedRoom = RoomStub.getRandomRoom();
        when(roomRepository.findRoomsByType_Id(any())).thenReturn(Collections.singletonList(expectedRoom));
        mockMvc.perform(getRequest("/rooms/type/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedRoom.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedRoom.getDescription())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedRoom.getPrice().toString())));
    }

    @Test
    void should_return_401_get_by_type() throws Exception {
        mockMvc.perform(getRequest("/1/rooms"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id() throws Exception {
        var request = RoomStub.getRoomRequest();
        request.setName("Name1");
        var expectedType = RoomStub.getRandomRoom();
        when(roomRepository.findById(any())).thenReturn(Optional.of(expectedType));
        when(roomRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        mockMvc.perform(putRequest("/rooms/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(request.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_update_by_id() throws Exception {
        var request = RoomStub.getRoomRequest();
        mockMvc.perform(putRequest("/rooms/1", request))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_update_by_id() throws Exception {
        var request = RoomStub.getRoomRequest();
        mockMvc.perform(putRequest("/rooms/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_400_update_by_id() throws Exception {
        var request = RoomStub.getRoomRequest();
        when(roomRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(putRequest("/rooms/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/rooms/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(roomRepository, atLeast(1)).deleteById(1L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/rooms/1"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/rooms/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


    private MockHttpServletRequestBuilder postRequest(String url, RoomRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, RoomRequest request) {
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