package com.prueba1.main.rest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.DTOs.Responses.DefaultMissionResponse;
import com.prueba1.main.rest.Mappers.MissionMapper;
import com.prueba1.main.rest.Services.MissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Missions", description = "Mission management and retrieval operations")
public class MissionController {

	@Autowired
	private MissionService service;

	@Operation(summary = "Get mission by ID", description = "Returns detailed information about a mission using its ID")
	@ApiResponse(responseCode = "200", description = "Mission retrieved successfully")
	@ApiResponse(responseCode = "404", description = "Mission not found")
	@GetMapping("mission/get-by-id")
	public ResponseEntity<DefaultMissionResponse> getById(@RequestParam Long missionId) {
		return ResponseEntity.ok(MissionMapper.toResponse(service.getMissionById(missionId)));
	}
}
