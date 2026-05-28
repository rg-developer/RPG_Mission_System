package com.prueba1.main.rest.Validations;

import java.util.ArrayList;
import java.util.List;


public class ActionResult {
	private boolean correct;
	private boolean existError;

	private List<String> errorMessages;
	private List<String> informationMessages;
	private List<String> warningMessages;
	
	
	
	public ActionResult() {
		this.errorMessages = new ArrayList<> ();
		this.informationMessages = new ArrayList<>();
		this.warningMessages = new ArrayList<>();
	}
	public boolean isCorrect() {
		return errorMessages.isEmpty() && warningMessages.isEmpty();
	}
	public boolean isExistError() {
		return errorMessages.isEmpty();
	}
	public String getCompleteErrorMessages() {
		StringBuilder stringBuilder = new StringBuilder();
		for (String errorMessage : errorMessages) {
			stringBuilder.append(errorMessage);
		}
		return stringBuilder.toString();
	}
	public String getCompleteInformationMessages() {
		StringBuilder stringBuilder = new StringBuilder();
		for (String informationMessage : informationMessages) {
			stringBuilder.append(informationMessage);
		}
		return stringBuilder.toString();
	}
	public String getCompleteWarningMessages() {
		StringBuilder stringBuilder = new StringBuilder();
		for (String warningMessage : warningMessages) {
			stringBuilder.append(warningMessage);
		}
		return stringBuilder.toString();
	}
	
	public void addErrorMessage (String errorMessage) {
		errorMessages.add(errorMessage);
	}
	
	public void addInformationMessage (String informationMessage) {
		informationMessages.add(informationMessage);
	}
	
	public void addWarningMessage (String warningMessage) {
		warningMessages.add(warningMessage);
	}
}
