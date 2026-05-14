package com.prueba1.main.rest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.Mappers.QuestLogMapper;
import com.prueba1.main.rest.Services.QuestLogService;

import DTOs.Character.AddCharacterToMissionDTO;
import DTOs.Responses.DefaultQuestLogResponse;

@RestController
public class QuestLogController {

	@Autowired
	private QuestLogService service;

	@PostMapping("quest-log/add-character-to-mission")
	public ResponseEntity<DefaultQuestLogResponse> addCharacterToMission(@RequestBody AddCharacterToMissionDTO params) {
		return ResponseEntity.ok(
				QuestLogMapper.toResponse(service.addCharacterToMission(params))
				);
	}

	@PutMapping("quest-log/start-mission")
	public ResponseEntity<List<DefaultQuestLogResponse>> startMission(@RequestParam Long missionId) {
		return ResponseEntity.ok(
				QuestLogMapper.toResponseList(service.startMission(missionId))
				);
	}

	@PutMapping("quest-log/complete-mission")
	public ResponseEntity<List<DefaultQuestLogResponse>> completeMission(@RequestParam Long missionId) {
		return ResponseEntity.ok(
				QuestLogMapper.toResponseList(service.completeMission(missionId))
				);
	}

	@PutMapping("quest-log/end-mission")
	public ResponseEntity<List<DefaultQuestLogResponse>> failMission(@RequestParam Long missionId) {
		return ResponseEntity.ok(
				QuestLogMapper.toResponseList(service.failMission(missionId))
				);
	}
}
