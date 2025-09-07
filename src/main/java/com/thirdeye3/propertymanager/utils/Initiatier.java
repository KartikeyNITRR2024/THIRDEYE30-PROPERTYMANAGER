package com.thirdeye3.propertymanager.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thirdeye3.propertymanager.dtos.Response;
import com.thirdeye3.propertymanager.dtos.ServiceStatus;
import com.thirdeye3.propertymanager.services.ConfigurationService;
import com.thirdeye3.propertymanager.services.PropertyService;

import jakarta.annotation.PostConstruct;

@Component
public class Initiatier {
	
    private static final Logger logger = LoggerFactory.getLogger(Initiatier.class);
    
	@Autowired
	ConfigurationService configurationService;
	
	@Autowired
    private DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate = new RestTemplate();
    
    @Value("${thirdeye.priority}")
    private Integer priority;
    
	@PostConstruct
    public void init() throws Exception{
        logger.info("Initializing Initiatier...");
    	TimeUnit.SECONDS.sleep(priority * 3);
        configurationService.generateFirstConfiguration();
        logger.info("Initiatier initialized.");
    }
	
	public List<ServiceStatus> updateAllInitiatier(Integer priority) {
        List<ServiceStatus> results = new ArrayList<>();
        List<String> services = discoveryClient.getServices();

        for (String serviceName : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);

            for (ServiceInstance instance : instances) {
                String url = instance.getUri() + "/api/updateinitiatier/" + priority;
                String status;

                try {
                    Response<String> response = restTemplate.getForObject(url, Response.class);

                    if (response != null && response.isSuccess()) {
                        status = response.getResponse();
                    } else if (response != null) {
                        status = "FAILED: " + response.getErrorMessage();
                    } else {
                        status = "FAILED: Null response";
                    }

                } catch (Exception e) {
                    logger.error("Failed to call {}: {}", url, e.getMessage());
                    status = "FAILED: " + e.getMessage();
                }

                results.add(new ServiceStatus(serviceName, url, status));
            }
        }

        return results;
    }
	
	public void refreshMemory()
	{
		logger.info("Going to refersh memory...");
		logger.info("Memory refreshed.");
	}
	
	

}

