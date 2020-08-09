package com.inoastrum.pharmapharmacyservice.web.mappers;

import com.inoastrum.pharmapharmacyservice.domain.Staff;
import com.inoastrum.pharmapharmacyservice.web.models.StaffDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@DecoratedWith(StaffMapperDecorater.class)
@Mapper(uses = {DateMapper.class})
public interface StaffMapper {

    @Mapping(target = "pharmacyId", source = "pharmacy.id")
    StaffDto staffToStaffDto(Staff staff);

    @Mapping(target = "pharmacy", ignore = true)
    Staff staffDtoToStaff(StaffDto staffDto);
}
