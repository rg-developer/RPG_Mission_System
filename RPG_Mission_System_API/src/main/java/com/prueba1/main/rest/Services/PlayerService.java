package com.prueba1.main.rest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Models.Player.Role;
import com.prueba1.main.rest.Repos.PlayerRepository;
import com.prueba1.main.rest.Validations.ActionResult;
import com.prueba1.main.rest.Validations.ValidationServices.PlayerValidationService;

import DTOs.Responses.UpdatePlayerRoleDto;
import Exceptions.ResourceNotFoundException;

@Service
public class PlayerService{

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private PlayerValidationService playerValidationService;
	
	public Player getByEmail (String email) {
		return playerRepository.getByEmail(email);
	}
	
	public boolean emailExists (String email) {
		ActionResult result = playerValidationService.playerExistsValidation(email);
		return !result.isExistError();
	}
	
	public boolean setPlayerRole (UpdatePlayerRoleDto updatePlayerRoleDto) {
		Player player = playerRepository.getById(updatePlayerRoleDto.getPlayerId());
		ActionResult result = playerValidationService.playerIsNullValidation(player);
		if (result.isExistError()) {
			throw new ResourceNotFoundException(result.getCompleteErrorMessages());
		}
		
		if (updatePlayerRoleDto.getRole() == Role.ADMIN) {
			player.setRole(Role.ADMIN);
			playerRepository.save(player);
			return true;
		}
		
		player.setRole(Role.USER);
		playerRepository.save(player);
		return true;
	}
}
