package com.thirdeye3.propertymanager.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thirdeye3.propertymanager.dtos.ServiceStatus;
import com.thirdeye3.propertymanager.entities.Configuration;
import com.thirdeye3.propertymanager.entities.Property;
import com.thirdeye3.propertymanager.exceptions.InvalidPropertyKeyException;
import com.thirdeye3.propertymanager.exceptions.PropertyNotFoundException;
import com.thirdeye3.propertymanager.exceptions.UnauthorizedUpdateException;
import com.thirdeye3.propertymanager.repositories.PropertyRepo;
import com.thirdeye3.propertymanager.services.ConfigurationService;
import com.thirdeye3.propertymanager.services.MachineService;
import com.thirdeye3.propertymanager.services.PropertyService;
import com.thirdeye3.propertymanager.services.TelegrambotService;
import com.thirdeye3.propertymanager.utils.Initiatier;
import com.thirdeye3.propertymanager.utils.PropertyValidator;

@Service
public class PropertyServiceImpl implements PropertyService {

    private static final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);

    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private MachineService machineService;
    
    @Autowired
    private TelegrambotService telegrambotService;

    private Map<String, Object> properties = null;

    @Autowired
    private ConfigurationService configurationService;
    
    @Autowired
    private Initiatier initiatier;
    
    @Value("${thirdeye.priority}")
    private Integer priority;

    @Override
    public void updateProperties() {
        Property property = propertyRepo.findById(configurationService.getConfigurationId())
                .orElseThrow(() -> new PropertyNotFoundException(configurationService.getConfigurationId()));

        Map<String, Object> map = Map.ofEntries(
            Map.entry("NO_OF_WEBSCRAPPER_MARKET", property.getNoOfWebscrapperMarket()),
            Map.entry("NO_OF_WEBSCRAPPER_USER", property.getNoOfWebscrapperUser()),
            Map.entry("START_TIME_HOUR", property.getStartTimeHour()),
            Map.entry("START_TIME_MINUTE", property.getStartTimeMinute()),
            Map.entry("START_TIME_SECOND", property.getStartTimeSecond()),
            Map.entry("END_TIME_HOUR", property.getEndTimeHour()),
            Map.entry("END_TIME_MINUTE", property.getEndTimeMinute()),
            Map.entry("END_TIME_SECOND", property.getEndTimeSecond()),
            Map.entry("MP_START_HOUR", property.getMpStartHour()),
            Map.entry("MP_START_MINUTE", property.getMpStartMinute()),
            Map.entry("MP_START_SECOND", property.getMpStartSecond()),
            Map.entry("MP_END_HOUR", property.getMpEndHour()),
            Map.entry("MP_END_MINUTE", property.getMpEndMinute()),
            Map.entry("MP_END_SECOND", property.getMpEndSecond()),
            Map.entry("EP_START_HOUR", property.getEpStartHour()),
            Map.entry("EP_START_MINUTE", property.getEpStartMinute()),
            Map.entry("EP_START_SECOND", property.getEpStartSecond()),
            Map.entry("EP_END_HOUR", property.getEpEndHour()),
            Map.entry("EP_END_MINUTE", property.getEpEndMinute()),
            Map.entry("EP_END_SECOND", property.getEpEndSecond()),
            Map.entry("SIZE_TO_LOAD_STOCK", property.getSizeToLoadStock()),
            Map.entry("MAXIMUM_MESSAGE_LENGTH", property.getMaximumMessageLength()),
            Map.entry("MAXIMUM_MESSAGE_READ_FROM_MESSAGE_BROKER", property.getMaximumMessageReadFromMessageBroker()),
            Map.entry("NO_OF_TELEGRAMBOT", property.getNoOfTelegrambot()),
            Map.entry("MAXIMUM_NO_OF_USERS", property.getMaximumNoOfUsers()),
            Map.entry("MAXIMUM_NO_OF_THRESOLD_PER_GROUP", property.getMaximumNoOfThresoldPerGroup()),
            Map.entry("MAXIMUM_NO_OF_HOLDED_STOCK_PER_USER", property.getMaximumNoOfHoldedStockPerUser()),
            Map.entry("MAXIMUM_NO_OF_THRESOLD_GROUP_PER_USER", property.getMaximumNoOfThresoldGroupPerUser()),
            Map.entry("TIME_GAP_LIST_FOR_THRESOLD_IN_SECONDS", property.getTimeGapListForThresoldInSeconds()),
            Map.entry("FILTER_FOR_TIME_THRESOLD", property.getFilterForTimeThresold()),
            Map.entry("BUFFER_TIME_GAP_IN_SECONDS", property.getBufferTimeGapInSeconds())
        );

        this.properties = map;
        logger.info("Loaded properties (uppercase keys): {}", properties);
    }

    @Override
    public void updateProperty(Map<String, Object> updates, String password) {
        if (!configurationService.allowToChange(password)) {
            throw new UnauthorizedUpdateException();
        }
        
        Property property = propertyRepo.findById(configurationService.getConfigurationId())
                .orElseThrow(() -> new PropertyNotFoundException(configurationService.getConfigurationId()));

        updates.forEach((key, value) -> {
            switch (key.toUpperCase()) {
                case "NO_OF_WEBSCRAPPER_MARKET" 				  -> {
                    property.setNoOfWebscrapperMarket((Integer) value);
                    machineService.updateMachines(1, property.getNoOfWebscrapperMarket());
                }
                case "NO_OF_WEBSCRAPPER_USER"   				  -> {
                    property.setNoOfWebscrapperUser((Integer) value);
                    machineService.updateMachines(2, property.getNoOfWebscrapperUser());
                }
                case "NO_OF_TELEGRAMBOT"   				          -> {
                    property.setNoOfTelegrambot((Integer) value);
                    machineService.updateMachines(3, property.getNoOfTelegrambot());
                }
                
                case "START_TIME_HOUR"              				  -> property.setStartTimeHour((Integer) value);
                case "START_TIME_MINUTE"            				  -> property.setStartTimeMinute((Integer) value);
                case "START_TIME_SECOND"            				  -> property.setStartTimeSecond((Integer) value);
                case "END_TIME_HOUR"                				  -> property.setEndTimeHour((Integer) value);
                case "END_TIME_MINUTE"              				  -> property.setEndTimeMinute((Integer) value);
                case "END_TIME_SECOND"              				  -> property.setEndTimeSecond((Integer) value);
                case "MP_START_HOUR"                				  -> property.setMpStartHour((Integer) value);
                case "MP_START_MINUTE"              				  -> property.setMpStartMinute((Integer) value);
                case "MP_START_SECOND"              				  -> property.setMpStartSecond((Integer) value);
                case "MP_END_HOUR"                  				  -> property.setMpEndHour((Integer) value);
                case "MP_END_MINUTE"                				  -> property.setMpEndMinute((Integer) value);
                case "MP_END_SECOND"                				  -> property.setMpEndSecond((Integer) value);
                case "EP_START_HOUR"                				  -> property.setEpStartHour((Integer) value);
                case "EP_START_MINUTE"              				  -> property.setEpStartMinute((Integer) value);
                case "EP_START_SECOND"              				  -> property.setEpStartSecond((Integer) value);
                case "EP_END_HOUR"                  				  -> property.setEpEndHour((Integer) value);
                case "EP_END_MINUTE"                				  -> property.setEpEndMinute((Integer) value);
                case "EP_END_SECOND"                				  -> property.setEpEndSecond((Integer) value);
                case "BUFFER_TIME_GAP_IN_SECONDS"   				  -> property.setBufferTimeGapInSeconds((Integer) value); 
                case "SIZE_TO_LOAD_STOCK"           				  -> property.setSizeToLoadStock((Integer) value); 
                case "MAXIMUM_MESSAGE_LENGTH"       				  -> property.setMaximumMessageLength((Integer) value); 
                case "MAXIMUM_MESSAGE_READ_FROM_MESSAGE_BROKER"       -> property.setMaximumMessageReadFromMessageBroker((Integer) value);
                case "MAXIMUM_NO_OF_USERS"                            -> property.setMaximumNoOfUsers((Integer) value);
                case "MAXIMUM_NO_OF_THRESOLD_PER_GROUP"               -> property.setMaximumNoOfThresoldPerGroup((Integer) value);
                case "MAXIMUM_NO_OF_HOLDED_STOCK_PER_USER"            -> property.setMaximumNoOfHoldedStockPerUser((Integer) value);
                case "MAXIMUM_NO_OF_THRESOLD_GROUP_PER_USER"          -> property.setMaximumNoOfThresoldGroupPerUser((Integer) value);
                case "TIME_GAP_LIST_FOR_THRESOLD_IN_SECONDS"          -> {
                	PropertyValidator.timeGapListForThresoldInSecondsValidator((String) value);
                	property.setTimeGapListForThresoldInSeconds((String) value);
                }
                case "FILTER_FOR_TIME_THRESOLD"                       -> {
                	PropertyValidator.filterForTimeThresoldValidater((String) value);
                	property.setFilterForTimeThresold((String) value);
                }
                default -> throw new InvalidPropertyKeyException(key);
            }
        });
        propertyRepo.save(property);
        updateProperties();
        initiatier.updateAllInitiatier(priority);
        logger.info("Updated property with id={} using updates: {}", configurationService.getConfigurationId(), updates);
    }

    @Override
    public Map<String, Object> getProperties() {
        if (properties == null) {
            updateProperties();
        }
        return this.properties;
    }
    
    @Override
    public Boolean isServicesUpdating()
    {
    	return false;
    }

    @Override
    public Map<String, Object> getPropertiesForWebscrapper(Integer machineid, String machineUniqueCode) {
        if (properties == null) {
            updateProperties();
        }
        Map<String, Object> propertiesForWebscrapper = new HashMap<>();
        propertiesForWebscrapper.put("MACHINE_NO", machineService.validateMachine(machineid, machineUniqueCode));
        propertiesForWebscrapper.put("START_TIME_HOUR", properties.get("START_TIME_HOUR"));
        propertiesForWebscrapper.put("START_TIME_MINUTE", properties.get("START_TIME_MINUTE"));
        propertiesForWebscrapper.put("START_TIME_SECOND", properties.get("START_TIME_SECOND"));
        propertiesForWebscrapper.put("END_TIME_HOUR", properties.get("END_TIME_HOUR"));
        propertiesForWebscrapper.put("END_TIME_MINUTE", properties.get("END_TIME_MINUTE"));
        propertiesForWebscrapper.put("END_TIME_SECOND", properties.get("END_TIME_SECOND"));
        return propertiesForWebscrapper;
    }
    
    @Override
    public Map<String, Object> getPropertiesForTelegramBot(Integer machineid, String machineUniqueCode) {
        if (properties == null) {
            updateProperties();
        }
        Map<String, Object> propertiesForTelegrambot = new HashMap<>();
        propertiesForTelegrambot.put("MACHINE_NO", machineService.validateMachine(machineid, machineUniqueCode));
        propertiesForTelegrambot.put("MAXIMUM_MESSAGE_READ_FROM_MESSAGE_BROKER", properties.get("MAXIMUM_MESSAGE_READ_FROM_MESSAGE_BROKER"));
        return propertiesForTelegrambot;
    }

	@Override
	public Map<String, Object> getPropertiesForFrontend() {
        if (properties == null) {
            updateProperties();
        }
        Map<String, Object> propertiesForFrontend = new HashMap<>();
        propertiesForFrontend.put("MAXIMUM_NO_OF_THRESOLD_PER_GROUP", properties.get("MAXIMUM_NO_OF_THRESOLD_PER_GROUP"));
        propertiesForFrontend.put("MAXIMUM_NO_OF_HOLDED_STOCK_PER_USER", properties.get("MAXIMUM_NO_OF_HOLDED_STOCK_PER_USER"));
        propertiesForFrontend.put("MAXIMUM_NO_OF_THRESOLD_GROUP_PER_USER", properties.get("MAXIMUM_NO_OF_THRESOLD_GROUP_PER_USER"));
        propertiesForFrontend.put("TIME_GAP_LIST_FOR_THRESOLD_IN_SECONDS", properties.get("TIME_GAP_LIST_FOR_THRESOLD_IN_SECONDS"));
        propertiesForFrontend.put("ALL_ACTIVE_BOTS", telegrambotService.getAllActiveBotNames().toString());
        return propertiesForFrontend;
	}
	

}
