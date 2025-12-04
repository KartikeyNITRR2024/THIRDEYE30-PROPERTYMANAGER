package com.thirdeye3.propertymanager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3.propertymanager.dtos.Response;
import com.thirdeye3.propertymanager.dtos.StatusCheckerDto;
import com.thirdeye3.propertymanager.services.StatusCheckerService;

@RestController
@RequestMapping("/pm/statuschecker")
public class MicroserviceStatusCheckerController {
	
	private static final Logger logger = LoggerFactory.getLogger(MicroserviceStatusCheckerController.class);

    @Autowired
    private StatusCheckerService statusCheckerService;

    @GetMapping("/active")
    public Response<List<StatusCheckerDto>> getAllActive() {
    	logger.info("Getting active status checker links");
        return new Response<>(true, 0, null, statusCheckerService.getAllActive());
    }
}

