package com.thirdeye3.propertymanager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thirdeye3.propertymanager.dtos.Response;
import com.thirdeye3.propertymanager.dtos.ServiceStatus;
import com.thirdeye3.propertymanager.utils.Initiatier;

@RestController
@RequestMapping("/pm/allserviceinitiatier")
public class AllServiceInitiatierController {

    private static final Logger logger = LoggerFactory.getLogger(AllServiceInitiatierController.class);

    @Autowired
    private Initiatier initiatier;
    
    @Value("${thirdeye.priority}")
    private Integer priority;

    @GetMapping("/{priority}")
    public Response<Boolean> updateAllInitiatier(@PathVariable("priority") Integer requestPriority) {
        try {
            logger.info("Updating all initiatier with priority: {}", priority);
            initiatier.updateAllInitiatier(requestPriority);
            return new Response<>(true, 0, null, true);
        } catch (Exception e) {
            logger.error("Error updating initiatier", e);
            return new Response<>(false, 0, "Failed to update initiatier", null);
        }
    }
}

