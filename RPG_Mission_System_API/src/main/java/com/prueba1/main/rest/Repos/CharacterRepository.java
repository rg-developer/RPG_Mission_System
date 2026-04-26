package com.prueba1.main.rest.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.Models.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
	@Query(value = "SELECT c FROM Character c WHERE c.id = :characterId")
	public Character getById(@Param("characterId") Long characterId);

}
