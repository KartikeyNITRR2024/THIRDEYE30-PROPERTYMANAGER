package com.thirdeye3.propertymanager.controllers;

import com.thirdeye3.propertymanager.dtos.Response;
import com.thirdeye3.propertymanager.dtos.ServiceStatus;
import com.thirdeye3.propertymanager.dtos.UpdateProperty;
import com.thirdeye3.propertymanager.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pm/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public Response<Map<String, Object>> getProperties() {
        return new Response<>(true, 0, null, propertyService.getProperties());
    }
    
    @GetMapping("/servicesupdating")
    public Response<Boolean> servicesUpdating() {
        return new Response<>(true, 0, null, propertyService.isServicesUpdating());
    }
    
    @GetMapping("/webscrapper/{id}/{code}")
    public Response<Map<String, Object>> getForWebscrapper(@PathVariable("id") Integer webscrapperId, @PathVariable("code") String webscrapperCode) {
        return new Response<>(true, 0, null, propertyService.getPropertiesForWebscrapper(webscrapperId, webscrapperCode));
    }
    
    @GetMapping("/telegrambot/{id}/{code}")
    public Response<Map<String, Object>> getForTelegrambot(@PathVariable("id") Integer telegrambotId, @PathVariable("code") String telegrambotCode) {
        return new Response<>(true, 0, null, propertyService.getPropertiesForTelegramBot(telegrambotId, telegrambotCode));
    }
    
    @GetMapping("/frontend")
    public Response<Map<String, Object>> getForFrontend() {
        return new Response<>(true, 0, null, propertyService.getPropertiesForFrontend());
    }

    @PostMapping("/update")
    public Response<Boolean> updateProperties(
            @RequestBody UpdateProperty updateProperty) {
    	propertyService.updateProperty(updateProperty.getUpdates(), updateProperty.getPassword());
        return new Response<>(true, 0, null, true);
    }
}
