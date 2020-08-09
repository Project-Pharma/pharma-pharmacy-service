package com.inoastrum.pharmapharmacyservice.web.controllers;

import com.inoastrum.pharmapharmacyservice.services.StaffService;
import com.inoastrum.pharmapharmacyservice.web.models.StaffDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/staff")
@Slf4j
@RestController
public class StaffController {

    private final StaffService staffService;

    @GetMapping("/{staffId}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable UUID staffId) {
        return new ResponseEntity<>(staffService.findStaffDtoById(staffId), HttpStatus.OK);
    }
}
