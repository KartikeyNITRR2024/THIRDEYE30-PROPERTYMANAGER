package com.thirdeye3.propertymanager.repositories;

import com.thirdeye3.propertymanager.entities.Telegrambot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TelegrambotRepo extends JpaRepository<Telegrambot, Long> {
    Optional<Telegrambot> findByName(String name);
    boolean existsByName(String name);
    List<Telegrambot> findByActiveTrue();
    List<Telegrambot> findByActiveTrueOrderByNameAsc();
}
