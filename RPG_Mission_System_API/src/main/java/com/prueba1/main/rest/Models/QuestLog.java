package com.prueba1.main.rest.Models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quests_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "character_id", foreignKey = @ForeignKey(name = "quest_log_character_FK"))
	@JsonBackReference
	private Character character;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mission_id", foreignKey = @ForeignKey(name = "quest_log_mission_FK"))
	@JsonBackReference
	private Mission mission;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Boolean failed;
	
	@Column(nullable = true)
	@JoinColumn (name = "started_at")
	private LocalDate startedAt;
	
	@Column(nullable = true)
	@JoinColumn (name = "completed_at")
	private LocalDate completedAt;

	public QuestLog(Character character, Mission mission, Status status, Boolean failed, LocalDate startedAt,
			LocalDate completedAt) {
		super();
		this.character = character;
		this.mission = mission;
		this.status = status;
		this.failed = failed;
		this.startedAt = startedAt;
		this.completedAt = completedAt;
	}
	
	
}
