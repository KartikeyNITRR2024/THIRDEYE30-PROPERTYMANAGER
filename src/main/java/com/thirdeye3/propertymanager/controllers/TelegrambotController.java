package com.thirdeye3.propertymanager.controllers;

import com.thirdeye3.propertymanager.dtos.TelegrambotDto;
import com.thirdeye3.propertymanager.services.TelegrambotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pm/telegrambots")
public class TelegrambotController {

    @Autowired
    private TelegrambotService telegrambotService;

    @PostMapping
    public ResponseEntity<TelegrambotDto> createBot(@RequestBody TelegrambotDto botDto) {
        return ResponseEntity.ok(telegrambotService.createTelegrambot(botDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBot(@PathVariable Long id) {
        telegrambotService.deleteTelegrambot(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<TelegrambotDto>> getAllBots() {
        return ResponseEntity.ok(telegrambotService.getAllTelegrambots());
    }

    @GetMapping("/active")
    public ResponseEntity<List<TelegrambotDto>> getAllActiveBots() {
        return ResponseEntity.ok(telegrambotService.getAllActiveBots());
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<TelegrambotDto> activateBot(@PathVariable Long id) {
        return ResponseEntity.ok(telegrambotService.activateBot(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<TelegrambotDto> deactivateBot(@PathVariable Long id) {
        return ResponseEntity.ok(telegrambotService.deactivateBot(id));
    }
}

