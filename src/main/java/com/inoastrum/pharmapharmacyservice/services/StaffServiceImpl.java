package com.inoastrum.pharmapharmacyservice.services;

import com.inoastrum.pharmapharmacyservice.domain.Staff;
import com.inoastrum.pharmapharmacyservice.exceptions.StaffNotFoundException;
import com.inoastrum.pharmapharmacyservice.repositories.StaffRepository;
import com.inoastrum.pharmapharmacyservice.web.mappers.StaffMapper;
import com.inoastrum.pharmapharmacyservice.web.models.StaffDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Override
    public StaffDto findStaffDtoById(UUID staffId) {
        return staffMapper.staffToStaffDto(staffRepository.findById(staffId).orElseThrow(StaffNotFoundException::new));
    }

    @Override
    public StaffDto saveNewStaff(StaffDto staffDto) {
        Staff staff = staffRepository.save(staffMapper.staffDtoToStaff(staffDto));
        staff.getPharmacy().getStaffs().add(staff);
        return staffMapper.staffToStaffDto(staff);
    }
}
