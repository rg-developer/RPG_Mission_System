package com.prueba1.main.rest.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Models.Player.Role;
import com.prueba1.main.rest.Repos.PlayerRepository;

import DTOs.Responses.SetPlayerRoleDto;
import Exceptions.ResourceNotFoundException;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	public ResponseEntity<List<Player>> getAll() {
		List<Player> result = playerRepository.getAll();
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(result);
	}
	
	
	public boolean emailExists (String email) {
		Player player = playerRepository.getByEmail(email);
		if (player == null) {
			return false;
		}
		return true;
	}
	
	public boolean setPlayerRole (SetPlayerRoleDto setPlayerRoleDto) {
		Player player = playerRepository.getById(setPlayerRoleDto.getPlayerId());
		
		if (player == null) {
			throw new ResourceNotFoundException("Player not found");
		}
		
		if (setPlayerRoleDto.getRole().equals("ADMIN")) {
			player.setRole(Role.ADMIN);
			playerRepository.save(player);
			return true;
		}
		
		player.setRole(Role.USER);
		playerRepository.save(player);
		return true;
		
	}
}
