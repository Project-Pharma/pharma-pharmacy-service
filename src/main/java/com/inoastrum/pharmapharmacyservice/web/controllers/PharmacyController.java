package com.inoastrum.pharmapharmacyservice.web.controllers;

import com.inoastrum.pharmapharmacyservice.services.PharmacyService;
import com.inoastrum.pharmapharmacyservice.web.models.PharmacyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/pharmacy")
@Slf4j
@RestController
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @GetMapping("/{pharmacyId}")
    public ResponseEntity<PharmacyDto> getPharmacyById(@PathVariable UUID pharmacyId) {
        return new ResponseEntity<>(pharmacyService.findPharmacyById(pharmacyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewPharmacy(@RequestBody @Validated PharmacyDto pharmacyDto) {
        return new ResponseEntity<>(pharmacyService.saveNewPharmacy(pharmacyDto).getId(), HttpStatus.CREATED);
    }

}
