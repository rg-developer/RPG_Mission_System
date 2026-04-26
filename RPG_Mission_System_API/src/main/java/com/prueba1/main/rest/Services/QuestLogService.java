package com.prueba1.main.rest.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Models.Mission;
import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Models.QuestLog;
import com.prueba1.main.rest.Models.Status;
import com.prueba1.main.rest.Models.Character;
import com.prueba1.main.rest.Repos.MissionRepository;
import com.prueba1.main.rest.Repos.PlayerRepository;
import com.prueba1.main.rest.Repos.QuestLogRepository;

import DTOs.Character.AddCharacterToMissionDTO;

@Service
public class QuestLogService {

	@Autowired
	private QuestLogRepository questLogRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired
	private CharacterService characterService;

	public ResponseEntity<QuestLog> addCharacterToMission(AddCharacterToMissionDTO params) {
		Player characterPlayer = playerRepository.getById(params.getPlayerId());
		if(characterPlayer.getId() == null) {
			return ResponseEntity.noContent().build(); 
		}
		
		Character characterFounded =  characterPlayer.getCharacterById(params.getCharacterId());
		if(characterFounded == null) {
			return ResponseEntity.noContent().build();
		}
		
		Mission mission = missionRepository.getById(params.getMissionId());
		if(mission == null) {
			return ResponseEntity.noContent().build();
		}
		
		if(mission.getMinRequiredLevel() > characterFounded.getLevel() || mission.getMaxRequiredLevel() < characterFounded.getLevel() ) {
			return ResponseEntity.badRequest().build();
		}
		
		List<QuestLog> questLogs = questLogRepository.getByMissionId(params.getMissionId());
		if (questLogs == null) {
			return ResponseEntity.badRequest().build();
		}
		
		QuestLog newQuestLog = new QuestLog(null, characterFounded, mission, Status.PENDING, false, null, null);
		QuestLog questLogAdded = questLogRepository.save(newQuestLog);
		
		if(questLogAdded == null) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(questLogAdded);
	}
	public ResponseEntity<List<QuestLog>> startMission(Long missionId) {
		ResponseEntity<List<QuestLog>> questLogsResponse = updateQuestLogsStatesOfMission(missionId, Status.IN_PROGRES);
		
		if (!questLogsResponse.equals(ResponseEntity.ok(questLogsResponse.getBody()))){
			return questLogsResponse;
		}
		
		List<QuestLog> questLogs = questLogsResponse.getBody();
		changeStartedMissionDate (questLogs);
		
		return questLogsResponse;
	}
	public ResponseEntity<List<QuestLog>> completeMission(Long missionId) {
			ResponseEntity<List<QuestLog>> questLogsResponse = updateQuestLogsStatesOfMission(missionId, Status.COMPLETED);
			
			if (!questLogsResponse.equals(ResponseEntity.ok(questLogsResponse.getBody()))){
				return questLogsResponse;
			}
			
			List<QuestLog> questLogs = questLogsResponse.getBody();
			changeCompletedMissionDate (questLogs);
			
			List <Character> charactersUpdated = characterService.addDefaultRewardsForCharacters(questLogs);
			if (charactersUpdated.size() != questLogs.size()) {
				return ResponseEntity.internalServerError().build();
			}
			
			return ResponseEntity.ok(questLogs); 
	}
	
	public ResponseEntity<List<QuestLog>> failMission(Long missionId) {
		ResponseEntity<List<QuestLog>> questLogsResponse = updateQuestLogsStatesOfMission(missionId, Status.FAILED);
		
		if (!questLogsResponse.equals(ResponseEntity.ok(questLogsResponse.getBody()))){
			return questLogsResponse;
		}
		
		List<QuestLog> questLogs = questLogsResponse.getBody();
		int countSavedLogs = 0;
		
		for (QuestLog questLog : questLogs) {
			questLog.setFailed(true);
			QuestLog savedLog = questLogRepository.save(questLog);
			
			if(savedLog != null) {
				countSavedLogs++;
			}
		}
		
		if(!(countSavedLogs == questLogs.size())) {
			return ResponseEntity.internalServerError().build();
		}
	
		return ResponseEntity.ok(questLogs);
	}
	
	private ResponseEntity<List<QuestLog>> updateQuestLogsStatesOfMission(Long missionId, Status status) {
		List<QuestLog> questLogs = questLogRepository.getByMissionId(missionId);
		if (questLogs == null) {
			return ResponseEntity.noContent().build();
		}
		
		for(int i = 0; i < questLogs.size(); i++) {
			if(questLogs.get(i).getStatus() == Status.PENDING && !(status == Status.IN_PROGRES)) {
				return ResponseEntity.badRequest().build();
			}
			if(questLogs.get(i).getStatus().equals(Status.IN_PROGRES) && (!(status == Status.COMPLETED) && !(status == Status.FAILED))) {
				return ResponseEntity.badRequest().build();
			}
			
			questLogs.get(i).setStatus(status);
		}
		
		List <QuestLog> updatedQuestLogs = questLogRepository.saveAll(questLogs);
		
		if(updatedQuestLogs == null) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(updatedQuestLogs);
		
	}
	
	private void changeStartedMissionDate (List <QuestLog >questLogs) {
		for (QuestLog questLog : questLogs) {
			questLog.setStartedAt(LocalDate.now());
			questLogRepository.save(questLog);
		}
	}
	
	private void changeCompletedMissionDate (List <QuestLog >questLogs) {
		for (QuestLog questLog : questLogs) {
			questLog.setCompletedAt(LocalDate.now());
			questLogRepository.save(questLog);
		}
	}
}
