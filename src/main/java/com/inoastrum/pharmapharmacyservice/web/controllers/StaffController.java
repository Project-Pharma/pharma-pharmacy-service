package com.inoastrum.pharmapharmacyservice.web.controllers;

import com.inoastrum.pharmapharmacyservice.services.StaffService;
import com.inoastrum.pharmapharmacyservice.web.models.StaffDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<UUID> createNewStaff(@RequestBody @Validated StaffDto staffDto) {
        return new ResponseEntity<>(staffService.saveNewStaff(staffDto).getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{staffId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStaff(@PathVariable UUID staffId, @RequestBody @Validated StaffDto staffDto) {
        staffService.updateStaff(staffId, staffDto);
    }

    @DeleteMapping("/{staffId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStaff(@PathVariable UUID staffId) {
        staffService.deleteRoleById(staffId);
    }
}
