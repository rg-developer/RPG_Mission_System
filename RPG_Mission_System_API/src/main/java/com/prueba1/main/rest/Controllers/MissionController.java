package com.prueba1.main.rest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.Mappers.MissionMapper;
import com.prueba1.main.rest.Services.MissionService;

import DTOs.Responses.DefaultMissionResponse;

@RestController
public class MissionController {

	@Autowired
	private MissionService service;

	@GetMapping("mission/get-by-id")
	public ResponseEntity<DefaultMissionResponse> getById(@RequestParam Long missionId) {
		return ResponseEntity.ok(
				MissionMapper.toResponse(
						service.getMissionById(missionId)
				));
	}
}
