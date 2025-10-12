package com.thirdeye3.propertymanager.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegrambotDto {
    private Long id;          
    private String name;
    private String token;     
    private Boolean active;
}
