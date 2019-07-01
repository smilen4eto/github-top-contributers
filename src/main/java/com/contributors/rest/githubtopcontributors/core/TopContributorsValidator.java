package com.contributors.rest.githubtopcontributors.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

public class TopContributorsValidator {
	private static final List<Integer> possibleNumberOfUsers = Stream.of(50,100,150).collect(Collectors.toList());
	private TopContributorsValidator() {
		
	}
	
    public static boolean isDataValid(String place, Integer count) {
    	if (StringUtils.isEmpty(place)) {
    		return false;
    	}
    	
    	if(count < 0) {
    		return false;
    	}
    	
    	if(!possibleNumberOfUsers.contains(count)) {
    		return false;
    	}
    	
    	return true;
    }

}
