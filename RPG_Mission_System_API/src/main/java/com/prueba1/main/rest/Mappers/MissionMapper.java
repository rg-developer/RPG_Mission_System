package com.prueba1.main.rest.Mappers;

import java.util.List;

import com.prueba1.main.rest.Models.Mission;

import DTOs.Responses.DefaultMissionResponse;

public class MissionMapper {

	public static DefaultMissionResponse toResponse(Mission mission) {
		return new DefaultMissionResponse(
				mission.getId(),
				mission.getTitle(),
				mission.getExperienceReward(),
				mission.getGoldReward()
				);
	}
	
	public static List<DefaultMissionResponse> toResponseList (List<Mission> missions){
		return missions.stream()
				.map(MissionMapper::toResponse)
				.toList();
	}

	
}
