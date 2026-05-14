package com.prueba1.main.rest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Services.PlayerService;

import lombok.NonNull;

@RestController
public class PlayerController {
	@Autowired
	private PlayerService service;

	@GetMapping("players/getAll")
	public ResponseEntity<List<Player>> getAll() {
		return service.getAll();
	}
	
	/*@PostMapping("/players/encrypt-password")
	public ResponseEntity<Boolean> encryptPassword(@RequestParam Long playerId) {
	    service.encodePassword(playerId);
	    return ResponseEntity.ok(true);
	}*/
}
