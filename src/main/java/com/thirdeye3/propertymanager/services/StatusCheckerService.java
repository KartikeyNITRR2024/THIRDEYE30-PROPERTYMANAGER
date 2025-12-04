package com.thirdeye3.propertymanager.services;

import java.util.List;
import com.thirdeye3.propertymanager.dtos.StatusCheckerDto;

public interface StatusCheckerService {

    StatusCheckerDto save(StatusCheckerDto dto);

    StatusCheckerDto getById(Long id);

    List<StatusCheckerDto> getAll();

    List<StatusCheckerDto> getAllActive();

    void delete(Long id);
}


