package com.inoastrum.pharmapharmacyservice.services;

import com.inoastrum.pharmapharmacyservice.domain.Staff;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService {
    @Override
    public Set<Staff> findAllStaffsByPharmacyId(UUID pharmacyId) {
        // todo impl
        return null;
    }
}
