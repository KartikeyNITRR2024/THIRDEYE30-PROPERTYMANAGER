package com.thirdeye3.propertymanager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MACHINE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Machine {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MACHINE_ID")
    private Long id;
    
    @Column(name = "TYPE_OF_MACHINE", nullable = false)
    private Integer typeOfMachine;
    
    @Column(name = "MACHINE_UNIQUE_CODE", nullable = false)
    private String machineUniqueCode;
    
    @Transient
    private Integer machineNo;
}
