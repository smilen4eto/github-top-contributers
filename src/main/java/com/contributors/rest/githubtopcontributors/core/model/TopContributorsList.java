package com.contributors.rest.githubtopcontributors.core.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = { "topContributors", "metadata"})
public class TopContributorsList {
	List<String> topContributors;
	Metadata metadata;
}
