package com.thirdeye3.propertymanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ServiceStatus {
    private String serviceName;
    private String instanceUrl;
    private String status;
}

