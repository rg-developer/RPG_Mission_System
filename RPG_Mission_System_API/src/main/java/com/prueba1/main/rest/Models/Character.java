package com.prueba1.main.rest.Models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "characters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn (name = "player_id")
	@JsonBackReference
	private Player player;
	
	private String name;
	private String race;
	
	@JoinColumn (name = "character_class")
	private String characterClass;
	
	private int level;
	
	@JoinColumn (name = "experience_points")
	private int experiencePoints;
	
	private int gold;
	
	@JoinColumn (name = "is_alive")
	private Boolean isAlive;
	
	@JoinColumn (name = "created_at")
	private LocalDate createdAt;
	
	@OneToMany(mappedBy= "character", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List <QuestLog> questLogs;
}
