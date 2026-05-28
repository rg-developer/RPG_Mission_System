package com.prueba1.main.rest.Validations.ValidationServices;

import com.prueba1.main.rest.Models.Mission;
import com.prueba1.main.rest.Validations.ActionResult;
import com.prueba1.main.rest.Validations.ValidationMessages.MissionsValidationServiceMessages;

public class MissionValidationService {
	
	public ActionResult missionIsNullValidation(Mission mission) {
		ActionResult result = new ActionResult();
		
		if(mission == null) {
			result.addErrorMessage(MissionsValidationServiceMessages.ERROR_MESSAGE_MISSION_NOT_FOUND);
		}
		
		return result;
	}
}
