package com.thirdeye3.propertymanager.services;
import com.thirdeye3.propertymanager.dtos.MachineInfo;

public interface MachineService {

	void updateMachines(Integer type, Integer newCount);

	void getAllMachines();

	Integer validateMachine(Integer machineid, String machineUniqueCode);

	MachineInfo getMachines(String type);

}
