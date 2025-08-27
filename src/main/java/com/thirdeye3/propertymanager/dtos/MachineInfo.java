package com.thirdeye3.propertymanager.dtos;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MachineInfo {
	Map<String, MachineDto> machineDtos;
	Integer type1Machines;
	Integer type2Machines;
	Integer telegramBotMachines;
}
