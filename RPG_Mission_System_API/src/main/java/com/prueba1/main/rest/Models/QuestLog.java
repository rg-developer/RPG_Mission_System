package com.prueba1.main.rest.Models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
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
	private Character character;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mission_id", foreignKey = @ForeignKey(name = "quest_log_mission_FK"))
	private Mission mission;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Boolean failed;
	
	@JoinColumn (name = "started_at")
	private LocalDate startedAt;
	
	@JoinColumn (name = "completed_at")
	private LocalDate completedAt;
	
	public enum Status {
		PENDING, IN_PROGRES, COMPLETED, FAILED
	}
}
