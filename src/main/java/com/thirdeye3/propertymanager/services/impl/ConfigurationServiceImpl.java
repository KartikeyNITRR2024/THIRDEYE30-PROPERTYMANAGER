package com.thirdeye3.propertymanager.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thirdeye3.propertymanager.entities.Configuration;
import com.thirdeye3.propertymanager.entities.Property;
import com.thirdeye3.propertymanager.exceptions.ConfigurationNotFoundException;
import com.thirdeye3.propertymanager.exceptions.InvalidConfigurationPasswordException;
import com.thirdeye3.propertymanager.repositories.ConfigurationRepo;
import com.thirdeye3.propertymanager.repositories.PropertyRepo;
import com.thirdeye3.propertymanager.services.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
	
	@Value("${thirdeye.propertyId}")
    private Long propertyId;

    @Value("${thirdeye.propertyPassword}")
    private String propertyPassword;
    
    @Autowired
    private PropertyRepo propertyRepo;

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

    @Autowired
    private ConfigurationRepo configurationRepo;

    private Long configurationId = null;

    @Override
    public void readConfiguration() {
        Optional<Configuration> configCheck = configurationRepo.findById(1L);
        if (configCheck.isPresent()) {
            Configuration config = configCheck.get();
            configurationId = config.getConfigId();
        } else {
            logger.error("Configuration with ID=1 not found");
            throw new ConfigurationNotFoundException("Configuration with ID=1 not found");
        }
    }

    @Override
    public Long getConfigurationId() {
        return propertyId;
    }
    
    @Override
    public Boolean isConfigUpdating() {
    	Configuration config = configurationRepo.findByConfigId(getConfigurationId());
        return config.getUpdatingServices();
    }

    @Override
    public Boolean allowToChange(String password) {
        Optional<Configuration> configCheck = configurationRepo.findById(1L);

        if (configCheck.isEmpty()) {
            logger.error("Configuration with ID=1 not found");
            throw new ConfigurationNotFoundException("Configuration with ID=1 not found");
        }

        Configuration config = configCheck.get();
        if (!config.getPassword().equals(password)) {
            logger.warn("Invalid password provided for configuration update");
            throw new InvalidConfigurationPasswordException("Invalid password provided");
        }

        logger.info("Allowed to change configuration");
        return true;
    }
    
	@Override
    public void generateFirstConfiguration()
    {
		Optional<Configuration> configCheck = configurationRepo.findById(1L);
        if (configCheck.isEmpty()) {
        	Configuration config = new Configuration(1L, propertyId, propertyPassword, false);
        	configurationRepo.save(config);
        	generateFirstProperty();
        } else {
        	logger.info("Configuration is allready present. Skiping creation.");
        }
    	
    }
	
	@Override
	public void updateUpdatingServices(Boolean check)
	{
		configurationRepo.updateUpdatingServicesByConfigId(getConfigurationId(), check);
	}
	
	@Override
    public void generateFirstProperty() {
        Property property = new Property(
                getConfigurationId(),
                0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                0, 0, 0,  
                0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                0, 0, "",
                ""
        );
        propertyRepo.save(property);
    }
}