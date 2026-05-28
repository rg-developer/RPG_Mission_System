package com.prueba1.main.rest.Validations.ValidationServices;

import org.springframework.beans.factory.annotation.Autowired;

import com.prueba1.main.rest.Models.Player;
import com.prueba1.main.rest.Services.PlayerService;
import com.prueba1.main.rest.Validations.ActionResult;
import com.prueba1.main.rest.Validations.ValidationMessages.PlayerValidationServiceMessages;

public class PlayerValidationService {
	@Autowired
	private PlayerService playerSevice;
	
	public ActionResult playerExistsValidation (String email) {
		ActionResult result = new ActionResult ();
		Player player = playerSevice.getByEmail(email);
		if (player == null) {
			result.addErrorMessage(PlayerValidationServiceMessages.ERROR_MESSAGE_PLAYER_NOT_FOUND);
		}
		return result;
	}
	
	public ActionResult playerIsNullValidation (Player player) {
		ActionResult result = new ActionResult ();
		
		if (player == null) {
			result.addErrorMessage(PlayerValidationServiceMessages.ERROR_MESSAGE_PLAYER_NOT_FOUND);
		}
		return result;
	}
}
