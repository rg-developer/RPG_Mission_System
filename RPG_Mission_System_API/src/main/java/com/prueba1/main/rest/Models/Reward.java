package com.prueba1.main.rest.Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "rewards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reward {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn (name = "mission_id")
	@JsonBackReference
	private Mission mission;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	private int amount;
	
	@JoinColumn (name = "item_name")
	@Column(nullable = true)
	private String itemName;
	
	@Column (columnDefinition = "TEXT")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private SourceType sourceType;
	
	@JoinColumn (name = "created_at")
	private LocalDate createdAt;
	
	public enum Type {
		GOLD, EXPERIENCE, ITEM
	}
	
	public enum SourceType{
		MISSION, ADMIN, EVENT
	}
	
	
}
