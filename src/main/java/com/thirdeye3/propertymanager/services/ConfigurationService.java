package com.thirdeye3.propertymanager.services;

public interface ConfigurationService {

	void readConfiguration();

	Long getConfigurationId();

	Boolean allowToChange(String password);

	void generateFirstConfiguration();

	void generateFirstProperty();

}
