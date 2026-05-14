package com.prueba1.main.rest.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.Models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
	@Query(value = "SELECT p FROM Player p")
	public List<Player> getAll();

	@Query(value = "SELECT p FROM Player p WHERE p.email = :email")
	public Player getByEmail(@Param("email") String email);
}
