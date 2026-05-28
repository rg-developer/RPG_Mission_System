package com.prueba1.main.rest.Validations.ValidationMessages;

public class QuestLogValidationServiceMessages {
	public static final String ERROR_MESSAGE_QUEST_LOG_NOT_FOUND = "No quest logs found";
	public static final String ERROR_MESSAGE_QUEST_LOG_NOT_PENDING = "The state must be on PENDING to put it IN_PROGRES";
	public static final String ERROR_MESSAGE_QUEST_LOG_NOT_IN_PROGRESS = "The state must be on IN_PROGRES to put it in COMPLETED or FAILED";
}
