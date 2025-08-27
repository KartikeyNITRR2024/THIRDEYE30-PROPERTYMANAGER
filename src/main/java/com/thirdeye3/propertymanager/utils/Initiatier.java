package com.thirdeye3.propertymanager.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thirdeye3.propertymanager.services.ConfigurationService;
import com.thirdeye3.propertymanager.services.PropertyService;

import jakarta.annotation.PostConstruct;

@Component
public class Initiatier {
	
    private static final Logger logger = LoggerFactory.getLogger(Initiatier.class);
    
	@Autowired
	ConfigurationService configurationService;
	
	@Autowired
	PropertyService propertyService;
    
    
	@PostConstruct
    public void init() throws Exception{
        logger.info("Initializing Initiatier...");
        configurationService.generateFirstConfiguration();
        propertyService.generateFirstProperty();
        logger.info("Initiatier initialized.");
    }

}

