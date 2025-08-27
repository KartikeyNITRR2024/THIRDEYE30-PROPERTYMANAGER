package com.thirdeye3.propertymanager.services;

import java.util.Map;

public interface PropertyService {

	Map<String, Object> getProperties();

	void updateProperty(Map<String, Object> updates, String password);

	void updateProperties();

	void generateFirstProperty();

	Map<String, Object> getPropertiesForWebscrapper(Integer machineid, String machineUniqueCode);

	Map<String, Object> getPropertiesForTelegramBot(Integer machineid, String machineUniqueCode);

}
