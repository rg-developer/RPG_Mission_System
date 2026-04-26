package com.prueba1.main.rest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.Models.QuestLog;
import com.prueba1.main.rest.Services.QuestLogService;

import DTOs.Character.AddCharacterToMissionDTO;

@RestController
public class QuestLogController {
	@Autowired
	private QuestLogService service;

	@PostMapping("quest-log/add-character-to-mission")
	public ResponseEntity<QuestLog> addCharacterToMission(@RequestBody AddCharacterToMissionDTO params) {
		return service.addCharacterToMission(params);
	}
	
	@PutMapping("quest-log/start-mission")
	public ResponseEntity<List<QuestLog>> startMission(@RequestParam("missionId") Long missionId) {
		return service.startMission(missionId);
	}
	
	@PutMapping("quest-log/complete-mission")
	public ResponseEntity<List<QuestLog>> completeMission(@RequestParam("missionId") Long missionId) {
		return service.completeMission(missionId);
	}
	
	@PutMapping("quest-log/end-mission")
	public ResponseEntity<List<QuestLog>> failMission(@RequestParam("missionId") Long missionId) {
		return service.failMission(missionId);
	}
}
