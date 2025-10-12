package com.thirdeye3.propertymanager.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TELEGRAMBOT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "NAME")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Telegrambot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TELEGRAMBOT_ID")
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "TOKEN", nullable = false)
    private String token;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = false;
}
