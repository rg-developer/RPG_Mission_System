package com.prueba1.main.rest.DTOs.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultMissionResponse {
	private Long id;
	private String title;
	private int experienceReward;
	private int goldReward;
}
