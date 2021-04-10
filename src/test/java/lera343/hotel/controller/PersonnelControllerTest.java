package lera343.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lera343.hotel.dto.TypeRequest;
import lera343.hotel.entity.Personnel;
import lera343.hotel.repository.PersonnelRepository;
import lera343.hotel.stubs.PersonnelStub;
import lera343.hotel.stubs.PersonnelStub;
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
class PersonnelControllerTest {
    @MockBean
    PersonnelRepository personnelRepository;
    
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
        var personnel = PersonnelStub.getRandomPersonnel();
        when(personnelRepository.save(any())).thenReturn(personnel);
        mockMvc.perform(postRequest("/personnels", personnel).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(personnel.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(personnel.getSurname())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(personnel.getEmail())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(personnel.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_save() throws Exception {
        var request = PersonnelStub.getRandomPersonnel();
        mockMvc.perform(postRequest("/personnels", request))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_save() throws Exception {
        var request = PersonnelStub.getRandomPersonnel();

        mockMvc.perform(postRequest("/personnels", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin() throws Exception {
        var expectedPersonnel = PersonnelStub.getRandomPersonnel();
        when(personnelRepository.findById(any())).thenReturn(Optional.of(expectedPersonnel));
        mockMvc.perform(getRequest("/personnels/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedPersonnel.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedPersonnel.getSurname())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedPersonnel.getEmail())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedPersonnel.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_by_id_user() throws Exception {
        var expectedPersonnel = PersonnelStub.getRandomPersonnel();
        when(personnelRepository.findById(any())).thenReturn(Optional.of(expectedPersonnel));
        mockMvc.perform(getRequest("/personnels/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedPersonnel.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedPersonnel.getDescription())));
    }

    @Test
    void should_return_401_get_by_id() throws Exception {
        mockMvc.perform(getRequest("/personnels/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_400_get_by_id() throws Exception {
        when(personnelRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(getRequest("/personnels/1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin() throws Exception {
        var expectedType = PersonnelStub.getRandomPersonnel();
        when(personnelRepository.findAll()).thenReturn(Collections.singletonList(expectedType));
        mockMvc.perform(getRequest("/personnels"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getSurname())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getEmail())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_all_user() throws Exception {
        var expectedType = PersonnelStub.getRandomPersonnel();
        when(personnelRepository.findAll()).thenReturn(Collections.singletonList(expectedType));
        mockMvc.perform(getRequest("/personnels"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getSurname())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getEmail())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    void should_return_401_get_all() throws Exception {
        mockMvc.perform(getRequest("/personnels"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_empty_array_get_all() throws Exception {
        when(personnelRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(getRequest("/personnels"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
    

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id() throws Exception {
        var request = PersonnelStub.getRandomPersonnel();
        request.setName("Name1");
        when(personnelRepository.findById(any())).thenReturn(Optional.of(request));
        when(personnelRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        mockMvc.perform(putRequest("/personnels/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(request.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(request.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_update_by_id() throws Exception {
        var request = PersonnelStub.getRandomPersonnel();
        mockMvc.perform(putRequest("/personnels/1", request))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_update_by_id() throws Exception {
        var request = PersonnelStub.getRandomPersonnel();
        mockMvc.perform(putRequest("/personnels/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_400_update_by_id() throws Exception {
        var request = PersonnelStub.getRandomPersonnel();
        when(personnelRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(putRequest("/personnels/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/personnels/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(personnelRepository, atLeast(1)).deleteById(1L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/personnels/1"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_deleted_by_id() throws Exception {
//        var request = PersonnelStub.getRandomPersonnel();
        mockMvc.perform(deleteRequest("/personnels/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


    private MockHttpServletRequestBuilder postRequest(String url, Personnel request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, Personnel request) {
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