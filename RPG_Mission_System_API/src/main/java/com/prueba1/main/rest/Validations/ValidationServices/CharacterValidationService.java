package com.prueba1.main.rest.Validations.ValidationServices;

import com.prueba1.main.rest.Validations.ActionResult;
import com.prueba1.main.rest.Validations.ValidationMessages.CharacterValidationServiceMessages;

import java.util.List;

import com.prueba1.main.rest.Models.Character;
import com.prueba1.main.rest.Models.Mission;
import com.prueba1.main.rest.Models.QuestLog;

public class CharacterValidationService {
	
	public ActionResult characterIsNullValidation(Character character) {
		ActionResult result = new ActionResult();
		
		if(character == null) {
			result.addErrorMessage(CharacterValidationServiceMessages.ERROR_MESSAGE_CHARACTER_NOT_FOUND);
		}
		
		return result;
	}
	
	public ActionResult characterLevelValidation(Mission mission, Character character) {
		ActionResult result = new ActionResult();
		
		if(mission.getMinRequiredLevel() > character.getLevel() || 
				   mission.getMaxRequiredLevel() < character.getLevel()) {
					result.addErrorMessage(CharacterValidationServiceMessages.ERROR_MESSAGE_INVALID_CHARACTER_LEVEL);
				}
		
		return result;
	}
	
	public ActionResult characterRewardsUpdatedValidation(List<QuestLog> questLogs, List<Character> characters) {
		ActionResult result = new ActionResult();
		
		if (characters.size() != questLogs.size()) {
			result.addErrorMessage(CharacterValidationServiceMessages.ERROR_MESSAGE_INVALID_REWARD);
		}
		
		return result;
	}
}
