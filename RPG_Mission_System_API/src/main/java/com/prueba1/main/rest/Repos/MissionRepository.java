package com.prueba1.main.rest.Repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.Models.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
	@Query(value = "SELECT m FROM Mission m WHERE m.id = :missionId")
	public Mission getById(@Param("missionId") Long missionId);

}
