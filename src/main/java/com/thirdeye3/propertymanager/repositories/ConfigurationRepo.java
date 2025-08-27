package com.thirdeye3.propertymanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thirdeye3.propertymanager.entities.Configuration;

@Repository
public interface ConfigurationRepo extends JpaRepository<Configuration, Long> {
}
