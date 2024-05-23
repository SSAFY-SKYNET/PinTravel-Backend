package com.ssafy.xmagazine.gpt;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GPTRequest {

	private String model;
	private List<Message> messages;
	private double temperature;
	private int maxTokens;
	private double topP;
	private int frequencyPenalty;
	private int presencePenalty;

	public GPTRequest(String model
		, String prompt
		, double temperature
		, int maxTokens
		, double topP
		, int frequencyPenalty
		, int presencePenalty) {
		this.model = model;
		this.messages = new ArrayList<>();
		this.messages.add(new Message("user", prompt));
		this.temperature = temperature;
		this.maxTokens = maxTokens;
		this.topP = topP;
		this.frequencyPenalty = frequencyPenalty;
		this.presencePenalty = presencePenalty;

	}
}