package com.thirdeye3.propertymanager.services.impl;

import com.thirdeye3.propertymanager.dtos.TelegrambotDto;
import com.thirdeye3.propertymanager.entities.Telegrambot;
import com.thirdeye3.propertymanager.repositories.TelegrambotRepo;
import com.thirdeye3.propertymanager.services.TelegrambotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegrambotServiceImpl implements TelegrambotService {

    @Autowired
    private TelegrambotRepo telegrambotRepository;

    private TelegrambotDto mapToDto(Telegrambot bot) {
        TelegrambotDto dto = new TelegrambotDto();
        dto.setId(bot.getId());
        dto.setName(bot.getName());
        dto.setToken(bot.getToken()); // optionally hide for responses
        dto.setActive(bot.getActive());
        return dto;
    }

    @Override
    public TelegrambotDto createTelegrambot(TelegrambotDto botDto) {
        if (telegrambotRepository.existsByName(botDto.getName())) {
            throw new RuntimeException("Bot name already exists!");
        }
        Telegrambot bot = new Telegrambot();
        bot.setName(botDto.getName());
        bot.setToken(botDto.getToken());
        bot.setActive(botDto.getActive() != null ? botDto.getActive() : false);
        return mapToDto(telegrambotRepository.save(bot));
    }

    @Override
    public TelegrambotDto updateTelegrambot(Long id, TelegrambotDto botDto) {
        Telegrambot existing = telegrambotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bot not found"));

        if (!existing.getName().equals(botDto.getName()) && telegrambotRepository.existsByName(botDto.getName())) {
            throw new RuntimeException("Bot name already exists!");
        }

        existing.setName(botDto.getName());
        existing.setToken(botDto.getToken());
        existing.setActive(botDto.getActive());
        return mapToDto(telegrambotRepository.save(existing));
    }

    @Override
    public void deleteTelegrambot(Long id) {
        telegrambotRepository.deleteById(id);
    }

    @Override
    public TelegrambotDto getTelegrambotById(Long id) {
        Telegrambot bot = telegrambotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bot not found"));
        return mapToDto(bot);
    }

    @Override
    public List<TelegrambotDto> getAllTelegrambots() {
        return telegrambotRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<TelegrambotDto> getAllActiveBots() {
        return telegrambotRepository.findByActiveTrue().stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public TelegrambotDto activateBot(Long id) {
        Telegrambot bot = telegrambotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bot not found"));
        bot.setActive(true);
        return mapToDto(telegrambotRepository.save(bot));
    }

    @Override
    public TelegrambotDto deactivateBot(Long id) {
        Telegrambot bot = telegrambotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bot not found"));
        bot.setActive(false);
        return mapToDto(telegrambotRepository.save(bot));
    }

    @Override
    public List<String> getAllActiveBotNames() {
        return telegrambotRepository.findByActiveTrueOrderByNameAsc()
                .stream()
                .map(Telegrambot::getName)
                .toList();
    }
}
