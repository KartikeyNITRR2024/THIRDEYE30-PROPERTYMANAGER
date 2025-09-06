package com.thirdeye3.propertymanager.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thirdeye3.propertymanager.dtos.MachineDto;
import com.thirdeye3.propertymanager.dtos.MachineInfo;
import com.thirdeye3.propertymanager.entities.Machine;
import com.thirdeye3.propertymanager.exceptions.InvalidMachineException;
import com.thirdeye3.propertymanager.repositories.MachineRepo;
import com.thirdeye3.propertymanager.services.MachineService;
import com.thirdeye3.propertymanager.utils.CodeGenerator;

@Service
public class MachineServiceImpl implements MachineService {
	
	private static final Logger logger = LoggerFactory.getLogger(MachineServiceImpl.class);

	@Autowired
	private MachineRepo machineRepo;
	
	@Autowired
	private CodeGenerator codeGenerator;
	
	private Map<String, Machine> machines = null;
	
	private Integer type1MachinesCount = null;
	private Integer type2MachinesCount = null;
	private Integer telegrambotMachinesCount = null;
	
	@Override
	public void getAllMachines() {
		List<Machine> machineList = machineRepo.findAll();
		machines = new HashMap<>();
		type1MachinesCount = 0;
		type2MachinesCount = 0;
		telegrambotMachinesCount = 0;
		int type1MachineNo = 0;
		int type2MachineNo = 0;
		int telegrambotMachineNo = 0;

		for(Machine machine : machineList) {
			if(machine.getTypeOfMachine() == 1) {
				type1MachinesCount++;
				type1MachineNo++;
				machine.setMachineNo(type1MachineNo);
			} else if(machine.getTypeOfMachine() == 2) {
				type2MachinesCount++;
				type2MachineNo++;
				machine.setMachineNo(type2MachineNo);
			} else if(machine.getTypeOfMachine() == 3) {
				telegrambotMachinesCount++;
				telegrambotMachineNo++;
				machine.setMachineNo(telegrambotMachineNo);
			}
			machines.put(machine.getId() + machine.getMachineUniqueCode() + machine.getTypeOfMachine(), machine);
		}
	}
	
	@Override
	@Transactional
	public void updateMachines(Integer type, Integer newCount) {
		if(type1MachinesCount == null || type2MachinesCount == null || machines == null) {
			getAllMachines();
		}

		if(type == 1 && newCount > type1MachinesCount) {
			logger.info("Adding {} type 1 machines", newCount - type1MachinesCount);
			ensureMinimumMachineLimit(1, newCount - type1MachinesCount);
		} else if(type == 1 && newCount < type1MachinesCount) {
			logger.info("Removing {} type 1 machines", type1MachinesCount - newCount);
			ensureMaximumMachineLimit(1, newCount);
		} else if(type == 2 && newCount > type2MachinesCount) {
			logger.info("Adding {} type 2 machines", newCount - type2MachinesCount);
			ensureMinimumMachineLimit(2, newCount - type2MachinesCount);
		} else if(type == 2 && newCount < type2MachinesCount) {
			logger.info("Removing {} type 2 machines", type2MachinesCount - newCount);
			ensureMaximumMachineLimit(2, newCount);
		} else if(type == 3 && newCount > telegrambotMachinesCount) {
			logger.info("Adding {} type telegrambot machines", newCount - type2MachinesCount);
			ensureMinimumMachineLimit(3, newCount - telegrambotMachinesCount);
		} else if(type == 3 && newCount < telegrambotMachinesCount) {
			logger.info("Removing {} type telegrambot machines", telegrambotMachinesCount - newCount);
			ensureMaximumMachineLimit(3, newCount);
		} 

		getAllMachines();
	}
	
	@Override
	public Integer validateMachine(Integer machineId, String machineUniqueCode) {
		if(machines == null) {
			getAllMachines();
		}
		String key = machineId + machineUniqueCode;
		if(!machines.containsKey(key)) {
			throw new InvalidMachineException("Invalid machine");
		}
		return machines.get(key).getMachineNo();
	}
	
	@Override
	public MachineInfo getMachines(String type) {
		if(type1MachinesCount == null || type2MachinesCount == null || machines == null) {
			getAllMachines();
		}
	    Map<String, MachineDto> machinesDto = new HashMap<>();
	    for (Map.Entry<String, Machine> entry : machines.entrySet()) {
	        Machine machine = entry.getValue();
	        if((type.equalsIgnoreCase("WEBSCRAPPER") && (machine.getTypeOfMachine() == 1 || machine.getTypeOfMachine() == 2))
	        		|| (type.equalsIgnoreCase("TELEGRAMBOT") && machine.getTypeOfMachine() == 3))
	        {
		        machinesDto.put(entry.getKey(),
		            new MachineDto(
		                machine.getId(),
		                machine.getTypeOfMachine(),
		                machine.getMachineUniqueCode(),
		                machine.getMachineNo()
		            )
		        );
	        }
	    }
	    if(type.equalsIgnoreCase("WEBSCRAPPER"))
	    {
	       return new MachineInfo(machinesDto, type1MachinesCount, type2MachinesCount, 0);
	    }
	    return new MachineInfo(machinesDto, 0, 0, telegrambotMachinesCount);
	}


	
	private void ensureMaximumMachineLimit(Integer typeOfMachine, int requiredCount) {
		logger.info("Ensuring maximum {} machines of type {}", requiredCount, typeOfMachine);
		List<Machine> machines = machineRepo.findByTypeOfMachineOrderById(typeOfMachine);
		if (machines.size() > requiredCount) {
			List<Machine> extraMachines = machines.subList(requiredCount, machines.size());
			logger.info("Removing machines: {}", extraMachines);
			machineRepo.deleteAll(extraMachines);
		}
	}
	
	private void ensureMinimumMachineLimit(Integer typeOfMachine, int additionalCount) {
		for (int i = 0; i < additionalCount; i++) {
			Machine newMachine = new Machine();
			newMachine.setTypeOfMachine(typeOfMachine);
			newMachine.setMachineUniqueCode(codeGenerator.generateUniqueCode(8));
			machineRepo.save(newMachine);
		}
	}
}
