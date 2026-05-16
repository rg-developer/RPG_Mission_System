package com.prueba1.main.rest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.Services.PlayerService;

import DTOs.Responses.UpdatePlayerRoleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Admin", description = "Admin operations for managing players and system configuration")
public class AdminController {
	@Autowired
	private PlayerService playerService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("admin/set-player-role")
	@Operation(
	        summary = "Update player role",
	        description = "Allows an admin to change the role of a player (USER / ADMIN)"
	    )
	    @ApiResponse(responseCode = "200", description = "Player role updated successfully")
	    @ApiResponse(responseCode = "400", description = "Invalid request data")
	    @ApiResponse(responseCode = "403", description = "Access denied")
	public ResponseEntity<Boolean> setPlayerRole(@RequestBody UpdatePlayerRoleDto updatePlayerRoleDto) {
		return ResponseEntity.ok(playerService.setPlayerRole(updatePlayerRoleDto));
	}
}
