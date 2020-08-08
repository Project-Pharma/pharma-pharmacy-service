package com.inoastrum.pharmapharmacyservice.web.mappers;

import com.inoastrum.pharmapharmacyservice.domain.Pharmacy;
import com.inoastrum.pharmapharmacyservice.services.StaffService;
import com.inoastrum.pharmapharmacyservice.web.models.PharmacyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class PharmacyMapperDecorator implements PharmacyMapper {

    private PharmacyMapper delegate;
    private StaffService staffService;

    @Autowired
    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    @Autowired
    public void setMapper(PharmacyMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public Pharmacy pharmacyDtoToPharmacy(PharmacyDto pharmacyDto) {
        Pharmacy pharma = delegate.pharmacyDtoToPharmacy(pharmacyDto);
        pharma.setStaffs(staffService.findAllStaffsByPharmacyId(pharmacyDto.getId()));
        return pharma;
    }
}
