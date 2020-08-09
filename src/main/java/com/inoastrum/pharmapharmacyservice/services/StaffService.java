package com.inoastrum.pharmapharmacyservice.services;

import com.inoastrum.pharmapharmacyservice.web.models.StaffDto;

import java.util.UUID;

public interface StaffService {
    StaffDto findStaffDtoById(UUID staffId);

    StaffDto saveNewPharmacy(StaffDto staffDto);
}
