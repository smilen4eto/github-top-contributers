package com.contributors.rest.githubtopcontributors.core;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.contributors.rest.githubtopcontributors.dataprovider.TopContributorsProvider;

public class TopContributorsActionsTest {
	@Mock 	
	private TopContributorsProvider topContributorsDefaultProvider;
	
	@InjectMocks
	private TopContributorsActions objectUnderTest;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getMaxContributorsForBarcelonaTest() {
		objectUnderTest.getTopContributorsForPlace("Barcelona", 150);
		verify(topContributorsDefaultProvider).findTopContributors("Barcelona", 150);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getIncorrectNumberOfContributors() {
		objectUnderTest.getTopContributorsForPlace("Sofia", 34);
		verify(topContributorsDefaultProvider).findTopContributors("Sofia", 34);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getContributorsWithEmptyPlace() {
		objectUnderTest.getTopContributorsForPlace("", 150);
		verify(topContributorsDefaultProvider).findTopContributors("", 150);
	}

}
