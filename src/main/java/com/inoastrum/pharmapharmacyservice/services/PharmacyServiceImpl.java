package com.inoastrum.pharmapharmacyservice.services;

import com.inoastrum.pharmapharmacyservice.domain.Pharmacy;
import com.inoastrum.pharmapharmacyservice.exceptions.PharmacyNotFoundException;
import com.inoastrum.pharmapharmacyservice.repositories.PharmacyRepository;
import com.inoastrum.pharmapharmacyservice.web.mappers.PharmacyMapper;
import com.inoastrum.pharmapharmacyservice.web.mappers.StaffMapper;
import com.inoastrum.pharmapharmacyservice.web.models.PharmacyDto;
import com.inoastrum.pharmapharmacyservice.web.models.StaffDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final PharmacyMapper pharmacyMapper;

    private StaffMapper staffMapper;

    @Autowired
    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Override
    public PharmacyDto findPharmacyDtoById(UUID pharmacyId) {
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

    @Override
    public void deletePharmacyById(UUID pharmacyId) {
        if (pharmacyRepository.findById(pharmacyId).isEmpty())
            throw new PharmacyNotFoundException();

        pharmacyRepository.deleteById(pharmacyId);
    }

    @Override
    public Pharmacy findPharmacyById(UUID pharmacyId) {
        return pharmacyRepository.findById(pharmacyId).orElseThrow(PharmacyNotFoundException::new);
    }

    @Override
    public List<StaffDto> findStaffsByPharmacyId(UUID pharmacyId) {
        Pharmacy returnedPharmacy = pharmacyRepository.findById(pharmacyId).orElseThrow(PharmacyNotFoundException::new);
        return returnedPharmacy.getStaffs().stream()
                .map(staffMapper::staffToStaffDto)
                .collect(Collectors.toList());
    }
}
