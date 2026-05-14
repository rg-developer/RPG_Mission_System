package com.prueba1.main.rest.Services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Models.Player.Role;
import com.prueba1.main.rest.Repos.PlayerRepository;
import com.prueba1.main.rest.Security.JwtUtil;

import DTOs.Auth.LoginDto;
import DTOs.Auth.RegisterDto;
import Exceptions.DuplicatedEmailException;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private PlayerService playerSrevice;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	public String loguin (LoginDto loguinDto) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(
				loguinDto.getEmail(),
				loguinDto.getPassword()
				));
		
		String token = jwtUtil.generateToken(loguinDto.getEmail());
		// Update LastLoguin Date
		
		return token;
	}
	
	public String register (RegisterDto registerDto) {
		boolean emailExists = playerSrevice.emailExists(registerDto.getEmail());
		if (emailExists) {
			throw new DuplicatedEmailException("This email has already been registered");
		}
		
		
		String passwordEncoded = new BCryptPasswordEncoder().encode(registerDto.getPassword());
		Player newPlayer = new Player(
				registerDto.getUsurname(),
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
