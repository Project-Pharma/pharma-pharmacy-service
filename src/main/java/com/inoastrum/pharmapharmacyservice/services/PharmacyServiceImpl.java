package com.inoastrum.pharmapharmacyservice.services;

import com.inoastrum.pharmapharmacyservice.domain.Pharmacy;
import com.inoastrum.pharmapharmacyservice.exceptions.PharmacyNotFoundException;
import com.inoastrum.pharmapharmacyservice.repositories.PharmacyRepository;
import com.inoastrum.pharmapharmacyservice.web.mappers.PharmacyMapper;
import com.inoastrum.pharmapharmacyservice.web.models.PharmacyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final PharmacyMapper pharmacyMapper;

    @Override
    public PharmacyDto findPharmacyById(UUID pharmacyId) {
        return pharmacyMapper.pharmacyToPharmacyDto(
                pharmacyRepository.findById(pharmacyId)
                        .orElseThrow(PharmacyNotFoundException::new));
    }

    @Override
    public PharmacyDto saveNewPharmacy(PharmacyDto pharmacyDto) {
        if (pharmacyDto.getDelivering() == null) pharmacyDto.setDelivering(false);

        return pharmacyMapper.pharmacyToPharmacyDto(
                pharmacyRepository.save(pharmacyMapper.pharmacyDtoToPharmacy(pharmacyDto))
        );
    }

    @Override
    public void updatePharmacy(UUID pharmacyId, PharmacyDto pharmacyDto) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElseThrow(PharmacyNotFoundException::new);

        if (pharmacyDto.getDelivering() != null)
            pharmacy.setDelivering(pharmacyDto.getDelivering());

        pharmacy.setName(pharmacyDto.getName());
        pharmacy.setAddress(pharmacyDto.getAddress());

        pharmacyRepository.save(pharmacy);
    }
}
