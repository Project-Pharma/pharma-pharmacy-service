package com.inoastrum.pharmapharmacyservice.services;

import com.inoastrum.pharmapharmacyservice.domain.Pharmacy;
import com.inoastrum.pharmapharmacyservice.web.models.PharmacyDto;
import com.inoastrum.pharmapharmacyservice.web.models.StaffDto;

import java.util.List;
import java.util.UUID;

public interface PharmacyService {
    PharmacyDto findPharmacyDtoById(UUID pharmacyId);

    PharmacyDto saveNewPharmacy(PharmacyDto pharmacyDto);

    void updatePharmacy(UUID pharmacyId, PharmacyDto pharmacyDto);

    void deletePharmacyById(UUID pharmacyId);

    Pharmacy findPharmacyById(UUID pharmacyId);

    List<StaffDto> findStaffsByPharmacyId(UUID pharmacyId);
}
