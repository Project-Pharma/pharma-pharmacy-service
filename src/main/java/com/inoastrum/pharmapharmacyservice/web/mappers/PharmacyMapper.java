package com.inoastrum.pharmapharmacyservice.web.mappers;

import com.inoastrum.pharmapharmacyservice.domain.Pharmacy;
import com.inoastrum.pharmapharmacyservice.web.models.PharmacyDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(PharmacyMapperDecorator.class)
public interface PharmacyMapper {
    PharmacyDto pharmacyToPharmacyDto(Pharmacy pharmacy);

    @Mapping(target = "staffs", ignore = true)
    Pharmacy pharmacyDtoToPharmacy(PharmacyDto pharmacyDto);
}
