package com.contributors.rest.githubtopcontributors.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contributors.rest.githubtopcontributors.core.model.TopContributorsList;
import com.contributors.rest.githubtopcontributors.dataprovider.TopContributorsProvider;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class TopContributorsActions {
	private final TopContributorsProvider topContributorsProvider;

    @Autowired
    public TopContributorsActions(TopContributorsProvider topContributorsProvider) {
        this.topContributorsProvider = topContributorsProvider;
    }

    public TopContributorsList getTopContributorsForPlace(String place, Integer count) {
        log.debug("Returning payload for getTopContributorsForPlace with place={}, total={}",
                place, count);
        if (!TopContributorsValidator.isDataValid(place, count)) {
            log.debug("Skipping getTopContributorsForPlace, because of invalid data. place: {}, total: {}.", place, count);
            throw new IllegalArgumentException("invalid data is provided");
        }
        return topContributorsProvider.findTopContributors(place, count);
    }

}
