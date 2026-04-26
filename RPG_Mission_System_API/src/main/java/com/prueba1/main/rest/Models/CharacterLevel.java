package com.prueba1.main.rest.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "characters_levels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterLevel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "character_id", foreignKey = @ForeignKey(name = "character_level_character_FK"))
	private Character character;
	
	private int level;
	@JoinColumn (name = "experience_points")
	private int experiencePoints;
}
