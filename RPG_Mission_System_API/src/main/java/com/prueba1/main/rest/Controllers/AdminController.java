package com.prueba1.main.rest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.Services.PlayerService;

import DTOs.Responses.SetPlayerRoleDto;

@RestController
public class AdminController {
	@Autowired
	private PlayerService playerService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("admins/set-player-role")
	public ResponseEntity<Boolean> setPlayerRole(@RequestBody SetPlayerRoleDto setPlayerRoleDto) {
		return ResponseEntity.ok(playerService.setPlayerRole(setPlayerRoleDto));
	}
}
