package com.prueba1.main.rest.Mappers;

import java.util.List;

import com.prueba1.main.rest.Models.QuestLog;

import DTOs.Responses.DefaultQuestLogResponse;

public class QuestLogMapper {

	public static DefaultQuestLogResponse toResponse(QuestLog questLog) {
		return new DefaultQuestLogResponse(
				questLog.getId(),
				questLog.getCharacter().getId(),
				questLog.getMission().getId(),
				questLog.getStatus(),
				questLog.getFailed(),
				questLog.getStartedAt(),
				questLog.getCompletedAt()
				);
	}
	
	public static List<DefaultQuestLogResponse> toResponseList (List<QuestLog> questsLogs){
		return questsLogs.stream()
				.map(QuestLogMapper::toResponse)
				.toList();
	}

	
}
