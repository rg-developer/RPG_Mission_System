package com.prueba1.main.rest.DTOs.Responses;

import com.prueba1.main.rest.Models.Player.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePlayerRoleDto {
	private Long playerId;
	private Role role;
}
