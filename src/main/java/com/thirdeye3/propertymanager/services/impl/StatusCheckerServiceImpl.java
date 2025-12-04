package com.thirdeye3.propertymanager.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3.propertymanager.dtos.StatusCheckerDto;
import com.thirdeye3.propertymanager.entities.StatusChecker;
import com.thirdeye3.propertymanager.repositories.StatusCheckerRepo;
import com.thirdeye3.propertymanager.services.StatusCheckerService;

@Service
public class StatusCheckerServiceImpl implements StatusCheckerService {

    private static final Logger logger = LoggerFactory.getLogger(StatusCheckerServiceImpl.class);

    @Autowired
    private StatusCheckerRepo repo;

    @Override
    public StatusCheckerDto save(StatusCheckerDto dto) {
        logger.info("Saving StatusChecker DTO: {}", dto);
        StatusChecker entity = toEntity(dto);
        StatusChecker saved = repo.save(entity);
        logger.info("Saved StatusChecker with ID: {}", saved.getId());
        return toDto(saved);
    }

    @Override
    public StatusCheckerDto getById(Long id) {
        logger.info("Fetching StatusChecker by ID: {}", id);
        return repo.findById(id)
                .map(entity -> {
                    logger.info("Found StatusChecker: {}", entity);
                    return toDto(entity);
                })
                .orElseGet(() -> {
                    logger.warn("No StatusChecker found with ID: {}", id);
                    return null;
                });
    }

    @Override
    public List<StatusCheckerDto> getAll() {
        logger.info("Fetching all StatusChecker entries");
        List<StatusChecker> entities = repo.findAll();
        logger.info("Total entries found: {}", entities.size());
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<StatusCheckerDto> getAllActive() {
        logger.info("Fetching all ACTIVE StatusChecker entries");
        List<StatusChecker> entities = repo.findByIsActiveTrue();
        logger.info("Total active entries found: {}", entities.size());
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting StatusChecker with ID: {}", id);
        repo.deleteById(id);
        logger.info("Deleted StatusChecker ID: {}", id);
    }

    private StatusCheckerDto toDto(StatusChecker entity) {
        return new StatusCheckerDto(
                entity.getId(),
                entity.getStatusCheckerName(),
                entity.getStatusCheckerUrl(),
                entity.getIsActive()
        );
    }

    private StatusChecker toEntity(StatusCheckerDto dto) {
        return new StatusChecker(
                dto.getId(),
                dto.getStatusCheckerName(),
                dto.getStatusCheckerUrl(),
                dto.getIsActive()
        );
    }
}
