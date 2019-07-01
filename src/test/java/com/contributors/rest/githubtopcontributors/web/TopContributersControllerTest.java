package com.contributors.rest.githubtopcontributors.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.contributors.rest.githubtopcontributors.core.TopContributorsActions;
import com.contributors.rest.githubtopcontributors.core.model.Metadata;
import com.contributors.rest.githubtopcontributors.core.model.TopContributorsList;
import com.contributors.rest.githubtopcontributors.web.controller.GithubTopContributorsController;


public class TopContributersControllerTest {
	private MockMvc mockMvc;
//	private TopContributorsList contributorsList;
	
	@Mock
	private TopContributorsActions topContributorsActions;
	
	@InjectMocks
	GithubTopContributorsController objectUnderTest;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(objectUnderTest).build();
	}
	
	@Test
	public void getAllContributorTest_For_Location() throws Exception {
		TopContributorsList contributorsList = new TopContributorsList();
		contributorsList.setMetadata(new Metadata("Sofia", 150));

		when(topContributorsActions.getTopContributorsForPlace(anyString(), anyInt()))
				.thenReturn(contributorsList);

		mockMvc.perform(get("/v1/top-contributors?place=Sofia&count=150")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(content().string(
						"{\"topContributors\":null,\"metadata\":{\"place\":\"Sofia\",\"total\":150}}"))
				.andExpect(status().is2xxSuccessful());

		verify(topContributorsActions).getTopContributorsForPlace("Sofia", 150);
	}
	
	@Test
	public void getAllContributors_When_Location_Has_None() throws Exception {
		TopContributorsList contributorsList = new TopContributorsList();
		contributorsList.setMetadata(new Metadata("Stambolijski", 0));

		when(topContributorsActions.getTopContributorsForPlace(anyString(), anyInt()))
				.thenReturn(contributorsList);

		mockMvc.perform(get("/v1/top-contributors?place=Stambolijski&count=150")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print())
				.andExpect(content().string(
						"{\"topContributors\":null,\"metadata\":{\"place\":\"Stambolijski\",\"total\":0}}"))
				.andExpect(status().is2xxSuccessful());

		verify(topContributorsActions).getTopContributorsForPlace("Stambolijski", 150);
	}
	
}
