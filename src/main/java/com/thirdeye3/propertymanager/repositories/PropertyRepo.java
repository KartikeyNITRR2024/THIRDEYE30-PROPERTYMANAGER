package com.thirdeye3.propertymanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thirdeye3.propertymanager.entities.Property;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
}
