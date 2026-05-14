package com.prueba1.main.rest.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import Exceptions.InvalidMissionException;
import Exceptions.InvalidRewardException;
import Exceptions.ResourceNotFoundException;

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

	public QuestLog addCharacterToMission(AddCharacterToMissionDTO params) {

		Player characterPlayer = playerRepository.getById(params.getPlayerId());
		if(characterPlayer.getId() == null) {
			throw new ResourceNotFoundException("Player not found");
		}
		
		Character characterFounded = characterPlayer.getCharacterById(params.getCharacterId());
		if(characterFounded == null) {
			throw new ResourceNotFoundException("Character not found");
		}
		
		Mission mission = missionRepository.getById(params.getMissionId());
		if(mission == null) {
			throw new ResourceNotFoundException("Mission not found");
		}
		
		if(mission.getMinRequiredLevel() > characterFounded.getLevel() || 
		   mission.getMaxRequiredLevel() < characterFounded.getLevel()) {
			throw new InvalidMissionException("Character level not valid for this mission");
		}
		
		List<QuestLog> questLogs = questLogRepository.getByMissionId(params.getMissionId());
		if (questLogs == null) {
			throw new InvalidMissionException("No quest logs found");
		}
		
		QuestLog newQuestLog = new QuestLog(null, characterFounded, mission, Status.PENDING, false, null, null);
		
		return questLogRepository.save(newQuestLog);
	}

	public List<QuestLog> startMission(Long missionId) {
		List<QuestLog> questLogs = updateQuestLogsStatesOfMission(missionId, Status.IN_PROGRES);
		changeStartedMissionDate(questLogs);
		return questLogs;
	}

	public List<QuestLog> completeMission(Long missionId) {

		List<QuestLog> questLogs = updateQuestLogsStatesOfMission(missionId, Status.COMPLETED);
		changeCompletedMissionDate(questLogs);
		
		List<Character> charactersUpdated = characterService.addDefaultRewardsForCharacters(questLogs);
		
		if (charactersUpdated.size() != questLogs.size()) {
			throw new InvalidRewardException("Not all characters recived the reward");
		}
		
		return questLogs;
	}

	public List<QuestLog> failMission(Long missionId) {
		List<QuestLog> questLogs = updateQuestLogsStatesOfMission(missionId, Status.FAILED);
		
		for (QuestLog questLog : questLogs) {
			questLog.setFailed(true);
			questLogRepository.save(questLog);
		}
		
		return questLogs;
	}

	private List<QuestLog> updateQuestLogsStatesOfMission(Long missionId, Status status) {
		List<QuestLog> questLogs = questLogRepository.getByMissionId(missionId);
		if (questLogs == null) {
			throw new RuntimeException("No quest logs found");
		}
		
		for (QuestLog ql : questLogs) {
			if(ql.getStatus() == Status.PENDING && status != Status.IN_PROGRES) {
				throw new InvalidMissionException("The state must be on PENDING to put it IN_PROGRES");
			}
			if(ql.getStatus() == Status.IN_PROGRES && 
			   (status != Status.COMPLETED && status != Status.FAILED)) {
				throw new InvalidMissionException("The state must be on IN_PROGRES to put it in COMPLETED or FAILED");
			}
			
			ql.setStatus(status);
		}
		
		return questLogRepository.saveAll(questLogs);
	}

	private void changeStartedMissionDate(List<QuestLog> questLogs) {
		for (QuestLog questLog : questLogs) {
			questLog.setStartedAt(LocalDate.now());
			questLogRepository.save(questLog);
		}
	}

	private void changeCompletedMissionDate(List<QuestLog> questLogs) {
		for (QuestLog questLog : questLogs) {
			questLog.setCompletedAt(LocalDate.now());
			questLogRepository.save(questLog);
		}
	}
}
