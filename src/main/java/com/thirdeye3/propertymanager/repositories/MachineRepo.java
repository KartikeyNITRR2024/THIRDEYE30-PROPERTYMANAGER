package com.thirdeye3.propertymanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thirdeye3.propertymanager.entities.Machine;

@Repository
public interface MachineRepo extends JpaRepository<Machine, Long> {
	List<Machine> findByTypeOfMachineOrderById(Integer typeOfMachine);
}
