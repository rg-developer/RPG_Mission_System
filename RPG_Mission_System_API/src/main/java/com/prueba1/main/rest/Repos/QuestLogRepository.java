package com.prueba1.main.rest.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba1.main.rest.Models.QuestLog;

public interface QuestLogRepository extends JpaRepository<QuestLog, Long> {
	@Query(value = "SELECT ql FROM QuestLog ql WHERE ql.mission.id = :missionId")
	public List<QuestLog> getByMissionId(@Param("missionId") Long missionId);
}
