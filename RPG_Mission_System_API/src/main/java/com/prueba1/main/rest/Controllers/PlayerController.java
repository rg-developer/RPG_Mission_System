package com.prueba1.main.rest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Services.PlayerService;

//@RequestMapping("/players")
@RestController
public class PlayerController {
	@Autowired
	private PlayerService service;

	@GetMapping("players/getAll")
	public ResponseEntity<List<Player>> getAll() {
		return service.getAll();
	}
}
