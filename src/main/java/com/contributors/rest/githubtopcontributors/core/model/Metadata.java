package com.contributors.rest.githubtopcontributors.core.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = { "place", "total" })
public class Metadata {
	String place;
	Integer total;
}
