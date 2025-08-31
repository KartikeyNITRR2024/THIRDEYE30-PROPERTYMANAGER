package com.thirdeye3.propertymanager.externalcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.thirdeye3.propertymanager.dtos.Response;
import com.thirdeye3.propertymanager.dtos.ServiceStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllMicroservicesClient {
    
    private static final Logger logger = LoggerFactory.getLogger(AllMicroservicesClient.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<ServiceStatus> getServiceStatuses(String endpointPath) {
        List<ServiceStatus> results = new ArrayList<>();
        List<String> services = discoveryClient.getServices();

        for (String serviceName : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);

            for (ServiceInstance instance : instances) {
                String url = instance.getUri() + endpointPath;
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
}
