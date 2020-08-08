package com.inoastrum.pharmapharmacyservice.repositories;

import com.inoastrum.pharmapharmacyservice.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PharmacyRepository extends JpaRepository<Pharmacy, UUID> {
}
