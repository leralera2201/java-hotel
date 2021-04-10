package lera343.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lera343.hotel.dto.TypeRequest;
import lera343.hotel.entity.Type;
import lera343.hotel.repository.TypeRepository;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

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
class TypeControllerTest {
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
        var request = TypeStub.getTypeRequest();
        var expectedType = TypeStub.getRandomType();
        when(typeRepository.save(any())).thenReturn(expectedType);
        mockMvc.perform(postRequest("/types", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_save() throws Exception {
        var request = TypeStub.getTypeRequest();
        mockMvc.perform(postRequest("/types", request))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_save() throws Exception {
        var request = TypeStub.getTypeRequest();

        mockMvc.perform(postRequest("/types", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    
    // get products by category
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void should_successfully_get_by_category_admin() throws Exception {
//        var expectedType = TypeStub.generateProduct();
//        var category = CategoryStub.generateCategory();
//        category.getProducts().add(expectedType);
//        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
//        mockMvc.perform(getRequest("/api/categories/1/products"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
//                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())))
//                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getCurrency().toString())));
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void should_successfully_get_by_category_user() throws Exception {
//        var expectedType = TypeStub.generateProduct();
//        var category = CategoryStub.generateCategory();
//        category.getProducts().add(expectedType);
//        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
//        mockMvc.perform(getRequest("/api/categories/1/products"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
//                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())))
//                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getCurrency().toString())));
//    }
//
//    @Test
//    void should_return_401_get_by_category() throws Exception {
//        mockMvc.perform(getRequest("/api/categories/100/products"))
//                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
//    }
    

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void should_return_400_get_by_category() throws Exception {
//        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
//        mockMvc.perform(getRequest("/api/categories/1/products"))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin() throws Exception {
        var expectedType = TypeStub.getRandomType();
        when(typeRepository.findById(any())).thenReturn(Optional.of(expectedType));
        mockMvc.perform(getRequest("/types/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_by_id_user() throws Exception {
        var expectedType = TypeStub.getRandomType();
        when(typeRepository.findById(any())).thenReturn(Optional.of(expectedType));
        mockMvc.perform(getRequest("/types/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    void should_return_401_get_by_id() throws Exception {
        mockMvc.perform(getRequest("/types/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_400_get_by_id() throws Exception {
        when(typeRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(getRequest("/types/1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin() throws Exception {
        var expectedType = TypeStub.getRandomType();
        when(typeRepository.findAll()).thenReturn(Collections.singletonList(expectedType));
        mockMvc.perform(getRequest("/types"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_successfully_get_all_user() throws Exception {
        var expectedType = TypeStub.getRandomType();
        when(typeRepository.findAll()).thenReturn(Collections.singletonList(expectedType));
        mockMvc.perform(getRequest("/types"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    void should_return_401_get_all() throws Exception {
        mockMvc.perform(getRequest("/types"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_empty_array_get_all() throws Exception {
        when(typeRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(getRequest("/types"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
    

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id() throws Exception {
        var request = TypeStub.getTypeRequest();
        request.setName("Name1");
        var expectedType = TypeStub.getRandomType();
        when(typeRepository.findById(any())).thenReturn(Optional.of(expectedType));
        when(typeRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        mockMvc.perform(putRequest("/types/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(request.getName())))
                .andExpect(MockMvcResultMatchers.content().string(containsString(expectedType.getDescription())));
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_update_by_id() throws Exception {
        var request = TypeStub.getTypeRequest();
        mockMvc.perform(putRequest("/types/1", request))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_update_by_id() throws Exception {
        var request = TypeStub.getTypeRequest();
        mockMvc.perform(putRequest("/types/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void should_return_400_update_by_id() throws Exception {
        var request = TypeStub.getTypeRequest();
        when(typeRepository.findById(any())).thenReturn(Optional.empty());
        mockMvc.perform(putRequest("/types/1", request).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/types/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(typeRepository, atLeast(1)).deleteById(1L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void should_return_403_deleted_by_id() throws Exception {
        mockMvc.perform(deleteRequest("/types/1"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void should_return_401_deleted_by_id() throws Exception {
//        var request = TypeStub.getTypeRequest();
        mockMvc.perform(deleteRequest("/types/1").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


    private MockHttpServletRequestBuilder postRequest(String url, TypeRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, TypeRequest request) {
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