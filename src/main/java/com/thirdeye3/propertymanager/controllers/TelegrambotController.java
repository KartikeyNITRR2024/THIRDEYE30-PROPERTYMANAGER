package com.thirdeye3.propertymanager.controllers;

import com.thirdeye3.propertymanager.dtos.Response;
import com.thirdeye3.propertymanager.dtos.TelegrambotDto;
import com.thirdeye3.propertymanager.services.TelegrambotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pm/telegrambots")
public class TelegrambotController {

    @Autowired
    private TelegrambotService telegrambotService;

    @PostMapping
    public Response<TelegrambotDto> createBot(@RequestBody TelegrambotDto botDto) {
        TelegrambotDto dto = telegrambotService.createTelegrambot(botDto);
        return new Response<>(true, 0, null, dto);
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteBot(@PathVariable Long id) {
        telegrambotService.deleteTelegrambot(id);
        return new Response<>(true, 0, null, null);
    }

    @GetMapping
    public Response<List<TelegrambotDto>> getAllBots() {
        List<TelegrambotDto> bots = telegrambotService.getAllTelegrambots();
        return new Response<>(true, 0, null, bots);
    }

    @GetMapping("/active")
    public Response<List<TelegrambotDto>> getAllActiveBots() {
        List<TelegrambotDto> bots = telegrambotService.getAllActiveBots();
        return new Response<>(true, 0, null, bots);
    }

    @PatchMapping("/{id}/activate")
    public Response<TelegrambotDto> activateBot(@PathVariable Long id) {
        TelegrambotDto dto = telegrambotService.activateBot(id);
        return new Response<>(true, 0, null, dto);
    }

    @PatchMapping("/{id}/deactivate")
    public Response<TelegrambotDto> deactivateBot(@PathVariable Long id) {
        TelegrambotDto dto = telegrambotService.deactivateBot(id);
        return new Response<>(true, 0, null, dto);
    }
}
