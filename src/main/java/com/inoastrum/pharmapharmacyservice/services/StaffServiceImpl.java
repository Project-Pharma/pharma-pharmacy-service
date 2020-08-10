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

    @Override
    public void updateStaff(UUID staffId, StaffDto staffDto) {
        Staff staff = staffRepository.findById(staffId).orElseThrow(StaffNotFoundException::new);

        staff.setName(staffDto.getName());
        staff.setRoleId(staffDto.getRoleId());
        staff.setPharmacy(staffMapper.staffDtoToStaff(staffDto).getPharmacy());

        staffRepository.save(staff);
    }

    @Override
    public void deleteRoleById(UUID staffId) {
        staffRepository.deleteById(staffId);
    }
}
