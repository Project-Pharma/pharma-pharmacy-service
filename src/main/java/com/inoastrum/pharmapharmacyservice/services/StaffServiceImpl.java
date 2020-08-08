package com.inoastrum.pharmapharmacyservice.services;

import com.inoastrum.pharmapharmacyservice.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

}
