package com.thirdeye3.propertymanager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STATUSCHECKER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusChecker {

	@Id
    @Column(name = "STATUSCHECKER_ID", nullable = false)
    private Long id;

    @Column(name = "STATUSCHECKER_NAME", nullable = false)
    private String statusCheckerName;

    @Column(name = "STATUSCHECKER_URL", nullable = false)
    private String statusCheckerUrl;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean isActive;
}
