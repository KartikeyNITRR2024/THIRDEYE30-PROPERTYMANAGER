package com.thirdeye3.propertymanager.services;

import java.util.List;
import java.util.Map;

import com.thirdeye3.propertymanager.dtos.ServiceStatus;

public interface PropertyService {

	Map<String, Object> getProperties();

	List<ServiceStatus> updateProperty(Map<String, Object> updates, String password);

	void updateProperties();

	Map<String, Object> getPropertiesForWebscrapper(Integer machineid, String machineUniqueCode);

	Map<String, Object> getPropertiesForTelegramBot(Integer machineid, String machineUniqueCode);

}
