package com.prueba1.main.rest.Models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false, unique = true)
	private String usurname;
	
	@Column (nullable = false, unique = true)
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@JoinColumn (name = "created_at")
	private LocalDate createdAt;
	
	@JoinColumn (name = "last_loguin")
	private LocalDate lastLoguin;
	
	@OneToMany(mappedBy= "player", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List <Character> characters;
	
	
	
	public enum Role {
		ADMIN, USER
	}
	
	public Character getCharacterById(Long id) {
		for (Character character: characters) {
			if (character.getId() == id) {
				return character;
			}
		}
		return null;
	}

	public Player(String usurname, String email, String password, Role role, LocalDate createdAt, LocalDate lastLoguin,
			List<Character> characters) {
		super();
		this.usurname = usurname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
		this.lastLoguin = lastLoguin;
		this.characters = characters;
	}
	
	
}
