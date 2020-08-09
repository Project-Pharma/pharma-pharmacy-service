package com.inoastrum.pharmapharmacyservice.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inoastrum.pharmapharmacyservice.services.PharmacyService;
import com.inoastrum.pharmapharmacyservice.web.models.PharmacyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@ExtendWith(MockitoExtension.class)
@WebMvcTest(PharmacyController.class)
class PharmacyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PharmacyService pharmacyService;

    @Autowired
    ObjectMapper objectMapper;


    PharmacyDto getValidDto() {
        return PharmacyDto.builder()
                .name("Test Pharmacy")
                .address("Test Street")
                .delivering(false)
                .build();
    }

    @Test
    void getPharmacyById() throws Exception {
        given(pharmacyService.findPharmacyById(any(UUID.class))).willReturn(getValidDto());

        ConstrainedFields fields = new ConstrainedFields(PharmacyDto.class);

        mockMvc.perform(get("/api/v1/pharmacy/{pharmacyId}", UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/pharmacy-get",
                        pathParameters(
                                parameterWithName("pharmacyId").description("UUID of desired pharmacy to get")
                        ),
                        responseFields(
                                fields.withPath("id").description("ID of the pharmacy"),
                                fields.withPath("version").description("API Version of the pharmacy"),
                                fields.withPath("createdDate").description("Creation Date"),
                                fields.withPath("lastModifiedDate").description("Last Modification Date"),
                                fields.withPath("name").description("Name of the pharmacy"),
                                fields.withPath("address").description("Address of the pharmacy"),
                                fields.withPath("delivering").description("Is this pharmacy delivering")
                        )));
    }

    @Test
    void saveNewPharmacy() throws Exception {
        String pharmacyDtoJson = objectMapper.writeValueAsString(getValidDto());

        ConstrainedFields fields = new ConstrainedFields(PharmacyDto.class);

        given(pharmacyService.saveNewPharmacy(any(PharmacyDto.class))).willReturn(getValidDto());

        mockMvc.perform(post("/api/v1/pharmacy/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pharmacyDtoJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andDo(document("v1/pharmacy-post",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("name").description("Name of the pharmacy"),
                                fields.withPath("address").description("Address of the pharmacy"),
                                fields.withPath("delivering").description("Is this pharmacy delivering").optional()
                        )));
    }

    @Test
    void updatePharmacy() throws Exception {
        String pharmacyDtoJson = objectMapper.writeValueAsString(getValidDto());

        ConstrainedFields fields = new ConstrainedFields(PharmacyDto.class);

        mockMvc.perform(put("/api/v1/pharmacy/{pharmacyId}", UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(pharmacyDtoJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isNoContent())
                .andDo(document("v1/pharmacy-put",
                        pathParameters(
                                parameterWithName("pharmacyId").description("UUID of desired pharmacy to update")
                        ),
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("name").description("Name of the pharmacy"),
                                fields.withPath("address").description("Address of the pharmacy"),
                                fields.withPath("delivering").description("Is this pharmacy delivering").optional()
                        )));
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}