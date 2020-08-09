package com.inoastrum.pharmapharmacyservice.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inoastrum.pharmapharmacyservice.services.StaffService;
import com.inoastrum.pharmapharmacyservice.web.models.PharmacyDto;
import com.inoastrum.pharmapharmacyservice.web.models.StaffDto;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@ExtendWith(MockitoExtension.class)
@WebMvcTest(StaffController.class)
class StaffControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StaffService staffService;

    @Autowired
    ObjectMapper objectMapper;

    StaffDto getValidDto() {
        return StaffDto.builder()
                .name("Test Staff")
                .pharmacyId(UUID.randomUUID())
                .roleId(UUID.randomUUID())
                .build();
    }

    @Test
    void getStaffById() throws Exception {
        given(staffService.findStaffDtoById(any(UUID.class))).willReturn(getValidDto());

        ConstrainedFields fields = new ConstrainedFields(StaffDto.class);

        mockMvc.perform(get("/api/v1/staff/{staffId}", UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/staff-get",
                        pathParameters(
                                parameterWithName("staffId").description("UUID of desired staff to get")
                        ),
                        responseFields(
                                fields.withPath("id").description("ID of the staff"),
                                fields.withPath("version").description("API Version of the staff"),
                                fields.withPath("createdDate").description("Creation Date"),
                                fields.withPath("lastModifiedDate").description("Last Modification Date"),
                                fields.withPath("name").description("Name of the staff"),
                                fields.withPath("pharmacyId").description("ID of the pharmacy that staff works in"),
                                fields.withPath("roleId").description("ID of the role of the staff")
                        )));
    }

    @Test
    void saveNewStaff() throws Exception {
        String staffDtoJson = objectMapper.writeValueAsString(getValidDto());

        ConstrainedFields fields = new ConstrainedFields(PharmacyDto.class);

        given(staffService.saveNewStaff(any(StaffDto.class))).willReturn(getValidDto());

        mockMvc.perform(post("/api/v1/staff/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(staffDtoJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andDo(document("v1/staff-post",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("name").description("Name of the staff"),
                                fields.withPath("pharmacyId").description("ID of the pharmacy"),
                                fields.withPath("roleId").description("ID of the role of staff")
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