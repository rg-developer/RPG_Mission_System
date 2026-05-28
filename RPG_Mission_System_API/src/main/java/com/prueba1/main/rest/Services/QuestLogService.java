package com.prueba1.main.rest.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Models.Mission;
import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Models.QuestLog;
import com.prueba1.main.rest.Models.Status;
import com.prueba1.main.rest.DTOs.Character.AddCharacterToMissionDTO;
import com.prueba1.main.rest.Exceptions.InvalidMissionException;
import com.prueba1.main.rest.Exceptions.InvalidRewardException;
import com.prueba1.main.rest.Exceptions.ResourceNotFoundException;
import com.prueba1.main.rest.Models.Character;
import com.prueba1.main.rest.Repos.MissionRepository;
import com.prueba1.main.rest.Repos.PlayerRepository;
import com.prueba1.main.rest.Repos.QuestLogRepository;
import com.prueba1.main.rest.Validations.ActionResult;
import com.prueba1.main.rest.Validations.ValidationServices.CharacterValidationService;
import com.prueba1.main.rest.Validations.ValidationServices.MissionValidationService;
import com.prueba1.main.rest.Validations.ValidationServices.PlayerValidationService;
import com.prueba1.main.rest.Validations.ValidationServices.QuestLogValidationService;

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
	
	@Autowired
	PlayerValidationService playerValidationService;
	
	@Autowired
	CharacterValidationService characterValidationService;
	
	@Autowired
	MissionValidationService missionValidationService;
	
	@Autowired
	QuestLogValidationService questLogValidationService;

	public QuestLog addCharacterToMission(AddCharacterToMissionDTO params) {

		Player characterPlayer = playerRepository.getById(params.getPlayerId());
		ActionResult playerIsNullValidation = playerValidationService.playerIsNullValidation(characterPlayer);
		if (playerIsNullValidation.isExistError()) {
			throw new ResourceNotFoundException(playerIsNullValidation.getCompleteErrorMessages());
		}
		
		Character characterFounded = characterPlayer.getCharacterById(params.getCharacterId());
		ActionResult characterIsNullValidation = characterValidationService.characterIsNullValidation(characterFounded);
		if (characterIsNullValidation.isExistError()) {
			throw new ResourceNotFoundException(characterIsNullValidation.getCompleteErrorMessages());
		}
		
		Mission mission = missionRepository.getById(params.getMissionId());
		ActionResult missionIsNullValidation = missionValidationService.missionIsNullValidation(mission);
		if (missionIsNullValidation.isExistError()) {
			throw new ResourceNotFoundException(missionIsNullValidation.getCompleteErrorMessages());
		}
	
		ActionResult characterLevelValidation = characterValidationService.characterLevelValidation(mission, characterFounded);
		if (characterLevelValidation.isExistError()) {
			throw new InvalidMissionException(characterLevelValidation.getCompleteErrorMessages());
		}
		
		List<QuestLog> questLogs = questLogRepository.getByMissionId(params.getMissionId());
		ActionResult questLogIsNullValidation = questLogValidationService.questLogIsNullValidation(questLogs);
		if (questLogIsNullValidation.isExistError()) {
			throw new InvalidMissionException(questLogIsNullValidation.getCompleteErrorMessages());
		}
		
		QuestLog newQuestLog = new QuestLog(null, characterFounded, mission, Status.PENDING, false, null, null);
		
		return questLogRepository.save(newQuestLog);
	}

	public List<QuestLog> startMission(Long missionId) {
		List<QuestLog> questLogsUpdatedStatesOfMission = updateQuestLogsStatesOfMission(missionId, Status.IN_PROGRES);
		List<QuestLog> questLogsFinal = changeStartedMissionDate(questLogsUpdatedStatesOfMission);
		return questLogRepository.saveAll(questLogsFinal);
	}

	public List<QuestLog> completeMission(Long missionId) {

		List<QuestLog> questLogsUpdatedStatesOfMission = updateQuestLogsStatesOfMission(missionId, Status.COMPLETED);
		List<QuestLog> questLogsFinal = changeCompletedMissionDate(questLogsUpdatedStatesOfMission);
		
		List<QuestLog> questLogsUpdated = questLogRepository.saveAll(questLogsFinal);
		List<Character> charactersUpdated = characterService.addDefaultRewardsForCharacters(questLogsFinal);
		
		ActionResult characterRewardsUpdatedValidation = characterValidationService.characterRewardsUpdatedValidation(questLogsUpdated, charactersUpdated);
		if (characterRewardsUpdatedValidation.isExistError()) {
			throw new InvalidRewardException(characterRewardsUpdatedValidation.getCompleteErrorMessages());
		}
		
		return questLogsUpdated;
	}

	public List<QuestLog> failMission(Long missionId) {
		List<QuestLog> questLogs = updateQuestLogsStatesOfMission(missionId, Status.FAILED);
		return questLogRepository.saveAll(questLogs);
	}

	private List<QuestLog> updateQuestLogsStatesOfMission(Long missionId, Status status) {
		List<QuestLog> questLogs = questLogRepository.getByMissionId(missionId);
		
		ActionResult questLogIsNullValidation = questLogValidationService.questLogIsNullValidation(questLogs);
		if (questLogIsNullValidation.isExistError()) {
			throw new RuntimeException(questLogIsNullValidation.getCompleteErrorMessages());
		}
		
		for (QuestLog ql : questLogs) {
			ActionResult questLogStateFlowValidation = questLogValidationService.questLogStateFlowValidation(ql, status);
			if (questLogStateFlowValidation.isExistError()) {
				throw new InvalidMissionException(questLogIsNullValidation.getCompleteErrorMessages());
			}
		
			if (status == Status.FAILED) {
				ql.setFailed(true);
			}
			if (status == Status.COMPLETED) {
				ql.setFailed(false);
			}
			
			ql.setStatus(status);
		}
		
		return questLogs;
	}

	private List<QuestLog> changeStartedMissionDate(List<QuestLog> questLogs) {
		for (QuestLog questLog : questLogs) {
			questLog.setStartedAt(LocalDate.now());
		}
		return questLogs;
	}

	private List<QuestLog> changeCompletedMissionDate(List<QuestLog> questLogs) {
		for (QuestLog questLog : questLogs) {
			questLog.setCompletedAt(LocalDate.now());
		}
		return questLogs;
	}
}
