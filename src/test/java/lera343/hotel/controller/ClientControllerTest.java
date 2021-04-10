package lera343.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lera343.hotel.dto.ClientRequest;
import lera343.hotel.dto.RoomRequest;
import lera343.hotel.repository.ClientRepository;
import lera343.hotel.repository.TypeRepository;
import lera343.hotel.stubs.ClientStub;
import lera343.hotel.stubs.ClientStub;
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
class ClientControllerTest {

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
        var request = ClientStub.getClientRequest();
        var expectedClient = ClientStub.getRandomClient();
        when(clientRepository.save(any())).thenReturn(expectedClient);
        mockMvc.perform(postRequest("/clients", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getSurname())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getEmail())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_save() throws Exception {
        var request = ClientStub.getClientRequest();
        mockMvc.perform(postRequest("/clients", request))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_save() throws Exception {
        var request = ClientStub.getClientRequest();

        mockMvc.perform(postRequest("/clients", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin() throws Exception {
        var expectedClient = ClientStub.getRandomClient();
        when(clientRepository.findById(any())).thenReturn(Optional.of(expectedClient));
        mockMvc.perform(getRequest("/clients/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getSurname())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getEmail())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_by_id_user() throws Exception {
        var expectedClient = ClientStub.getRandomClient();
        when(clientRepository.findById(any())).thenReturn(Optional.of(expectedClient));
        mockMvc.perform(getRequest("/clients/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getSurname())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getEmail())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getDescription())));
    }

    @Test
    void should_return_401_get_by_id() throws Exception {
        mockMvc.perform(getRequest("/clients/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_400_get_by_id() throws Exception {
        when(clientRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(getRequest("/clients/1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin() throws Exception {
        var expectedClient = ClientStub.getRandomClient();
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(expectedClient));
        mockMvc.perform(getRequest("/clients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_all_user() throws Exception {
        var expectedClient = ClientStub.getRandomClient();
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(expectedClient));
        mockMvc.perform(getRequest("/clients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getDescription())));
    }

    @Test
    void should_return_401_get_all() throws Exception {
        mockMvc.perform(getRequest("/clients"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_empty_array_get_all() throws Exception {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(getRequest("/clients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id() throws Exception {
        var request = ClientStub.getClientRequest();
        request.setName("Name1");
        var expectedClient = ClientStub.getRandomClient();
        when(clientRepository.findById(any())).thenReturn(Optional.of(expectedClient));
        when(clientRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        mockMvc.perform(putRequest("/clients/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(request.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedClient.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_update_by_id() throws Exception {
        var request = ClientStub.getClientRequest();
        mockMvc.perform(putRequest("/clients/1", request))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_update_by_id() throws Exception {
        var request = ClientStub.getClientRequest();
        mockMvc.perform(putRequest("/clients/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_400_update_by_id() throws Exception {
        var request = ClientStub.getClientRequest();
        when(clientRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(putRequest("/clients/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/clients/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(clientRepository, atLeast(1)).deleteById(1L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/clients/1"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/clients/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


    private MockHttpServletRequestBuilder postRequest(String url, ClientRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, ClientRequest request) {
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