package com.thirdeye3.propertymanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;


import com.thirdeye3.propertymanager.entities.Configuration;

@Repository
public interface ConfigurationRepo extends JpaRepository<Configuration, Long> {
	
	Configuration findByConfigId(Long configId);
	
    @Modifying
    @Transactional
    @Query("UPDATE Configuration c SET c.updatingServices = :status WHERE c.configId = :configId")
    int updateUpdatingServicesByConfigId(Long configId, Boolean status);

}
