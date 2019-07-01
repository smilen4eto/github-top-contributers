package com.contributors.rest.githubtopcontributors.web.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.contributors.rest.githubtopcontributors.core.TopContributorsActions;
import com.contributors.rest.githubtopcontributors.core.model.TopContributorsList;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@RestController
@RequestMapping("/v1/top-contributors")
@Validated
@Slf4j
public class GithubTopContributorsController {
	private final TopContributorsActions topContibutorsActions;
	private static final String DEFAULT_PLACE = "Barcelona";
	private static final int DEFAULT_COUNT = 50;

	
	@Autowired
	public GithubTopContributorsController(TopContributorsActions topContibutorsActions) {
		this.topContibutorsActions = topContibutorsActions;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public TopContributorsList getTopContributorsByPlace(
			@RequestParam(defaultValue = DEFAULT_PLACE)
			@Pattern(regexp = "^[a-zA-Z]*$")
			@NotNull
			String place, 
			@RequestParam(defaultValue = DEFAULT_COUNT+"") 
			@Positive
			@NotNull
			Integer count) {

        log.debug("Calling /top-contributors returning all contributors for place");

        return topContibutorsActions.getTopContributorsForPlace(place, count);

    }

}
