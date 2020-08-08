package com.inoastrum.pharmapharmacyservice.repositories;

import com.inoastrum.pharmapharmacyservice.domain.Pharmacy;
import com.inoastrum.pharmapharmacyservice.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface StaffRepository extends JpaRepository<Staff, UUID> {
    Set<Staff> findAllByPharmacy(Pharmacy pharmacy);
}
