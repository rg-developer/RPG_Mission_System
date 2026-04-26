package com.prueba1.main.rest.Models;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "missions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@Column(columnDefinition= "TEXT")
	private String description;
	
	@JoinColumn (name = "min_required_level")
	private int minRequiredLevel;
	
	@JoinColumn (name = "max_required_level")
	private int maxRequiredLevel;
	
	@JoinColumn (name = "experience_reward")
	private int experienceReward;
	
	@JoinColumn (name = "gold_reward")
	private int goldReward;
	
	@JoinColumn (name = "completed_at")
	private LocalDate completedAt;
	
	@OneToMany(mappedBy= "mission", cascade = CascadeType.ALL)
	private List <QuestLog> questLogs;
	
	@OneToMany(mappedBy= "mission", cascade = CascadeType.ALL)
	private List <Reward> rewards;
}
