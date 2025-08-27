package com.thirdeye3.propertymanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MachineDto {
	private Long id;
    private Integer typeOfMachine;
    private String machineUniqueCode;
    private Integer machineNo;
}
