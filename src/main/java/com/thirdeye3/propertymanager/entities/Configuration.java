package com.thirdeye3.propertymanager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "CONFIGURATION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Configuration {
	
	@Id
	@Column(name = "CONFIGURATION_ID", nullable = false)
    private Long id;
    
    @Column(name = "CONFIG_ID", nullable = false)
    private Long configId;
    
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @Column(name = "UPDATING_SERVICES", nullable=false)
    private Boolean updatingServices;
}
