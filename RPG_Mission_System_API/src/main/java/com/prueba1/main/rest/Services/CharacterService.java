package com.prueba1.main.rest.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Models.Character;
import com.prueba1.main.rest.Models.Mission;
import com.prueba1.main.rest.Models.QuestLog;
import com.prueba1.main.rest.Repos.CharacterRepository;

@Service
public class CharacterService {

	@Autowired
	private CharacterRepository characterRepository;
	
	public List<Character> addDefaultRewardsForCharacters (List<QuestLog> questLogs) {
		
		List<Character> charactersUpdated = new ArrayList<>();
		for (QuestLog questLog : questLogs) {
			Mission questMission = questLog.getMission();
			Character character = questLog.getCharacter();
			
			character.setExperiencePoints(character.getExperiencePoints() + questMission.getExperienceReward());
			character.setGold(character.getGold() + questMission.getGoldReward());
			
			Character characterUpdated = characterRepository.save(character);
			charactersUpdated.add(characterUpdated);
		}
		return charactersUpdated;
	}
}
