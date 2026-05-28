package com.prueba1.main.rest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Exceptions.ResourceNotFoundException;
import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Repos.PlayerRepository;
import com.prueba1.main.rest.Security.CustomUserDetails;
import com.prueba1.main.rest.Validations.ActionResult;
import com.prueba1.main.rest.Validations.ValidationServices.PlayerValidationService;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private PlayerValidationService playerValidationService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Player player = playerRepository.getByEmail(email);
		ActionResult result = playerValidationService.playerIsNullValidation(player);
		if (result.isExistError()) {
			throw new ResourceNotFoundException(result.getCompleteErrorMessages());
		}
		
		return new CustomUserDetails(player);
	}
}
