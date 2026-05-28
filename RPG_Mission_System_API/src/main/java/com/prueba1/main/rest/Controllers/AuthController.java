package com.prueba1.main.rest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba1.main.rest.DTOs.Auth.LoginDto;
import com.prueba1.main.rest.DTOs.Auth.RegisterDto;
import com.prueba1.main.rest.Services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Authentication", description = "Authentication operations (login & register)")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Operation(summary = "User login", description = "Authenticates a user and returns a JWT token")
	@ApiResponse(responseCode = "200", description = "Login successful, JWT returned")
	@ApiResponse(responseCode = "401", description = "Invalid credentials")
	@PostMapping("auth/login")
	public ResponseEntity<String> loguin(@RequestBody LoginDto loginDto) {
		return ResponseEntity.ok(authService.login(loginDto));
	}

	@Operation(summary = "User registration", description = "Creates a new user account in the system")
	@ApiResponse(responseCode = "200", description = "User registered successfully")
	@ApiResponse(responseCode = "400", description = "Invalid registration data")
	@PostMapping("auth/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		return ResponseEntity.ok(authService.register(registerDto));
	}
}
