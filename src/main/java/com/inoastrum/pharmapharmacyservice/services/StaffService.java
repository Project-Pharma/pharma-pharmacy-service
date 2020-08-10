package com.inoastrum.pharmapharmacyservice.services;

import com.inoastrum.pharmapharmacyservice.web.models.StaffDto;

import java.util.UUID;

public interface StaffService {
    StaffDto findStaffDtoById(UUID staffId);

    StaffDto saveNewStaff(StaffDto staffDto);

    void updateStaff(UUID staffId, StaffDto staffDto);

    void deleteRoleById(UUID staffId);
}
