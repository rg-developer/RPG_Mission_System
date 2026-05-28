package com.prueba1.main.rest.Validations.ValidationServices;

import org.springframework.beans.factory.annotation.Autowired;

import com.prueba1.main.rest.Services.PlayerService;
import com.prueba1.main.rest.Validations.ActionResult;
import com.prueba1.main.rest.Validations.ValidationMessages.AuthValidationServiceMessages;

public class AuthValidationService {
	@Autowired
	private PlayerService playerService;
	
	public ActionResult emailExistsValidation(String email) {
		ActionResult result = new ActionResult();
		
		boolean emailExists = playerService.emailExists(email);
		if (emailExists) {
			result.addErrorMessage(AuthValidationServiceMessages.ERROR_MESSAGE_EMAIL_ALREADY_REGISTRED);
		}
		
		return result;
	}

}
