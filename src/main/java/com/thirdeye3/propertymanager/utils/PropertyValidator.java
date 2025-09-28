package com.thirdeye3.propertymanager.utils;

import com.thirdeye3.propertymanager.exceptions.InvalidPropertyKeyException;

public class PropertyValidator {

	public static Boolean filterForTimeThresoldValidater(String s) {
	    String[] parts = s.split(",");
	    int n = parts.length;
	    if (n % 3 != 0) throw new InvalidPropertyKeyException("Improper filter for time threshold");
	    int[] arr = new int[n];
	    for (int i = 0; i < n; i++) {
	        try {
	            arr[i] = Integer.parseInt(parts[i].trim());
	            if (arr[i] < 0) throw new InvalidPropertyKeyException("Improper filter for time threshold");
	        } catch (NumberFormatException e) {
	            throw new InvalidPropertyKeyException("Improper filter for time threshold");
	        }
	    }
	    if (arr[0] != 0) throw new InvalidPropertyKeyException("Improper filter for time threshold");
	    for (int i = 0; i < n; i += 3) {
	        int a = arr[i], b = arr[i + 1], c = arr[i + 2];
	        if (c > b - a || (i >= 3 && a != arr[i - 2] + 1))
	            throw new InvalidPropertyKeyException("Improper filter for time threshold");
	    }
	    return true;
	}
	
	public static Boolean timeGapListForThresoldInSecondsValidator(String s) {
	    String[] parts = s.split(",");
	    int prev = -1;

	    for (String part : parts) {
	        int num;
	        try {
	            num = Integer.parseInt(part.trim());
	        } catch (NumberFormatException e) {
	            throw new InvalidPropertyKeyException("Invalid time gap for thresold");
	        }
	        if (num <= 0 || num <= prev) {
	            throw new InvalidPropertyKeyException("Invalid time gap for thresold");
	        }
	        prev = num;
	    }
	    return true;
	}

}
