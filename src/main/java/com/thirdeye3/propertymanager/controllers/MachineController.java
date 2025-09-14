package com.thirdeye3.propertymanager.controllers;

import com.thirdeye3.propertymanager.dtos.MachineInfo;
import com.thirdeye3.propertymanager.dtos.Response;
import com.thirdeye3.propertymanager.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pm/machines")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @GetMapping("/webscrapper")
    public Response<MachineInfo> getWebscrapperMachines() {
        return new Response<>(true, 0, null, machineService.getMachines("WEBSCRAPPER"));
    }
    
    @GetMapping("/telegrambot")
    public Response<MachineInfo> getTelegrambotMachines() {
        return new Response<>(true, 0, null, machineService.getMachines("TELEGRAMBOT"));
    }
}