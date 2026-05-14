package com.prueba1.main.rest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.Services.AuthService;

import DTOs.Auth.LoginDto;
import DTOs.Auth.RegisterDto;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("auth/login")
	public ResponseEntity<String> loguin(@RequestBody LoginDto loginDto) {
		return ResponseEntity.ok(authService.loguin(loginDto));
	}
	
	@PostMapping("auth/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		return ResponseEntity.ok(authService.register(registerDto));
	}
}
