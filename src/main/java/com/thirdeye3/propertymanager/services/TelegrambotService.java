package com.thirdeye3.propertymanager.services;

import com.thirdeye3.propertymanager.dtos.TelegrambotDto;

import java.util.List;

public interface TelegrambotService {

    TelegrambotDto createTelegrambot(TelegrambotDto botDto);

    TelegrambotDto updateTelegrambot(Long id, TelegrambotDto botDto);

    void deleteTelegrambot(Long id);

    TelegrambotDto getTelegrambotById(Long id);

    List<TelegrambotDto> getAllTelegrambots();

    List<TelegrambotDto> getAllActiveBots();

    TelegrambotDto activateBot(Long id);

    TelegrambotDto deactivateBot(Long id);

    List<String> getAllActiveBotNames();
}
