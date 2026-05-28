package com.prueba1.main.rest.Validations.ValidationServices;

import com.prueba1.main.rest.Validations.ActionResult;
import com.prueba1.main.rest.Validations.ValidationMessages.QuestLogValidationServiceMessages;

import java.util.List;

import com.prueba1.main.rest.Models.QuestLog;
import com.prueba1.main.rest.Models.Status;

public class QuestLogValidationService {
	
	public ActionResult questLogIsNullValidation(List<QuestLog> questLogs) {
		ActionResult result = new ActionResult();
		
		if(questLogs == null) {
			result.addErrorMessage(QuestLogValidationServiceMessages.ERROR_MESSAGE_QUEST_LOG_NOT_FOUND);
		}
		
		return result;
	}
	
	public ActionResult questLogStateFlowValidation(QuestLog questLog, Status status) {
		ActionResult result = new ActionResult();
		
		if(questLog.getStatus() == Status.PENDING && status != Status.IN_PROGRES) {
			result.addErrorMessage(QuestLogValidationServiceMessages.ERROR_MESSAGE_QUEST_LOG_NOT_PENDING);
			return result;
		}
		
		if(questLog.getStatus() == Status.IN_PROGRES && 
		   (status != Status.COMPLETED && status != Status.FAILED)) {
			result.addErrorMessage(QuestLogValidationServiceMessages.ERROR_MESSAGE_QUEST_LOG_NOT_IN_PROGRESS);
		}
		return result;
	}
}
