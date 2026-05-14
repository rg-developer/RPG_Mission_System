package com.prueba1.main.rest.Mappers;

import java.util.List;

import com.prueba1.main.rest.Models.Player;

import DTOs.Responses.DefaultPlayerResponse;

public class PlayerMapper {

	public static DefaultPlayerResponse toResponse(Player player) {
		return new DefaultPlayerResponse(
				player.getId(),
				player.getUsurname(),
				player.getCharacters()
				);
	}
	
	public static List<DefaultPlayerResponse> toResponseList (List<Player> missions){
		return missions.stream()
				.map(PlayerMapper::toResponse)
				.toList();
	}
}
