package com.thirdeye3.propertymanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusCheckerDto {
	private Long id;
    private String statusCheckerName;
    private String statusCheckerUrl;
    private Boolean isActive;
}
