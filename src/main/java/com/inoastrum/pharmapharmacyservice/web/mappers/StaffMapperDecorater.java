package com.inoastrum.pharmapharmacyservice.web.mappers;

import com.inoastrum.pharmapharmacyservice.domain.Staff;
import com.inoastrum.pharmapharmacyservice.services.PharmacyService;
import com.inoastrum.pharmapharmacyservice.web.models.StaffDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class StaffMapperDecorater implements StaffMapper {
    private StaffMapper delegate;
    private PharmacyService pharmacyService;

    @Autowired
    public void setDelegate(StaffMapper delegate) {
        this.delegate = delegate;
    }

    @Autowired
    public void setPharmacyService(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @Override
    public Staff staffDtoToStaff(StaffDto staffDto) {
        Staff staff = delegate.staffDtoToStaff(staffDto);

        staff.setPharmacy(pharmacyService.findPharmacyById(staffDto.getPharmacyId()));

        return staff;
    }
}
