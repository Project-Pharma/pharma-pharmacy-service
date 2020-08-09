package com.inoastrum.pharmapharmacyservice.services;

import com.inoastrum.pharmapharmacyservice.web.models.PharmacyDto;

import java.util.UUID;

public interface PharmacyService {
    PharmacyDto findPharmacyById(UUID pharmacyId);

    PharmacyDto saveNewPharmacy(PharmacyDto pharmacyDto);

    void updatePharmacy(UUID pharmacyId, PharmacyDto pharmacyDto);
}
