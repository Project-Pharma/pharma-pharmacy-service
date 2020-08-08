package com.inoastrum.pharmapharmacyservice.services;

import com.inoastrum.pharmapharmacyservice.domain.Staff;

import java.util.Set;
import java.util.UUID;

public interface StaffService {

    Set<Staff> findAllStaffsByPharmacyId(UUID pharmacyId);
}
