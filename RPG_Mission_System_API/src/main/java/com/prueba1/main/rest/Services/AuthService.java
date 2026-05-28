package com.prueba1.main.rest.Services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.DTOs.Auth.LoginDto;
import com.prueba1.main.rest.DTOs.Auth.RegisterDto;
import com.prueba1.main.rest.Exceptions.DuplicatedEmailException;
import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Models.Player.Role;
import com.prueba1.main.rest.Repos.PlayerRepository;
import com.prueba1.main.rest.Security.JwtUtil;
import com.prueba1.main.rest.Validations.ActionResult;
import com.prueba1.main.rest.Validations.ValidationServices.AuthValidationService;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private AuthValidationService authValidationService;
	
	public String login (LoginDto loginDto) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getEmail(),
				loginDto.getPassword()
				));
		
		String token = jwtUtil.generateToken(loginDto.getEmail());
		// Update LastLoguin Date
		
		return token;
	}
	
	public String register (RegisterDto registerDto) {
		ActionResult emailExistsValidation = authValidationService.emailExistsValidation(registerDto.getEmail());
		
		if (emailExistsValidation.isExistError()) {
			throw new DuplicatedEmailException(emailExistsValidation.getCompleteErrorMessages());
		}
		
		
		String passwordEncoded = new BCryptPasswordEncoder().encode(registerDto.getPassword());
		Player newPlayer = new Player(
				registerDto.getUsername(),
				registerDto.getEmail(),
				passwordEncoded,
				Role.USER,
				LocalDate.now(),
				LocalDate.now(),
				null
				); 
		
		playerRepository.save(newPlayer);
		
		return jwtUtil.generateToken(newPlayer.getEmail());
	}
}
