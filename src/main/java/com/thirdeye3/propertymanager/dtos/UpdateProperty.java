package com.thirdeye3.propertymanager.dtos;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UpdateProperty {
	private String password;
    private Map<String, Object> updates;
}
