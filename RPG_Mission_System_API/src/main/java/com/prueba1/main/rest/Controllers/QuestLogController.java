package com.prueba1.main.rest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.DTOs.Character.AddCharacterToMissionDTO;
import com.prueba1.main.rest.DTOs.Responses.DefaultQuestLogResponse;
import com.prueba1.main.rest.Mappers.QuestLogMapper;
import com.prueba1.main.rest.Services.QuestLogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Quest Log", description = "Core system for managing mission progression (assign, start, complete, fail)")
public class QuestLogController {

	@Autowired
	private QuestLogService service;

	@Operation(summary = "Assign character to mission", description = "Creates a QuestLog entry linking a character to a mission if level requirements are met")
	@ApiResponse(responseCode = "200", description = "Character assigned to mission successfully")
	@ApiResponse(responseCode = "400", description = "Invalid request or level requirements not met")
	@PostMapping("quest-log/add-character-to-mission")
	public ResponseEntity<DefaultQuestLogResponse> addCharacterToMission(@RequestBody AddCharacterToMissionDTO params) {
		return ResponseEntity.ok(QuestLogMapper.toResponse(service.addCharacterToMission(params)));
	}

	@Operation(summary = "Start mission", description = "Changes QuestLog status from PENDING to IN_PROGRESS and sets start date")
	@ApiResponse(responseCode = "200", description = "Mission started successfully")
	@ApiResponse(responseCode = "400", description = "Invalid state transition")
	@PutMapping("quest-log/start-mission")
	public ResponseEntity<List<DefaultQuestLogResponse>> startMission(@RequestParam Long missionId) {
		return ResponseEntity.ok(QuestLogMapper.toResponseList(service.startMission(missionId)));
	}

	@Operation(summary = "Complete mission", description = "Marks mission as COMPLETED, sets completion date and applies rewards (XP, Gold)")
	@ApiResponse(responseCode = "200", description = "Mission completed successfully")
	@ApiResponse(responseCode = "400", description = "Invalid state transition or reward error")
	@PutMapping("quest-log/complete-mission")
	public ResponseEntity<List<DefaultQuestLogResponse>> completeMission(@RequestParam Long missionId) {
		return ResponseEntity.ok(QuestLogMapper.toResponseList(service.completeMission(missionId)));
	}

	@Operation(summary = "Fail mission", description = "Marks mission as FAILED and flags QuestLog as failed")
	@ApiResponse(responseCode = "200", description = "Mission marked as failed")
	@ApiResponse(responseCode = "400", description = "Invalid state transition")
	@PutMapping("quest-log/end-mission")
	public ResponseEntity<List<DefaultQuestLogResponse>> failMission(@RequestParam Long missionId) {
		return ResponseEntity.ok(QuestLogMapper.toResponseList(service.failMission(missionId)));
	}
}
