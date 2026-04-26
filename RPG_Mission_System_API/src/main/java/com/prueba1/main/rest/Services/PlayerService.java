package com.prueba1.main.rest.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Repos.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository repository;

	public ResponseEntity<List<Player>> getAll() {
		List<Player> result = repository.getAll();
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(result);
	}
}
