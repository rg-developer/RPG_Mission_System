package com.prueba1.main.rest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Exceptions.ResourceNotFoundException;
import com.prueba1.main.rest.Models.Mission;
import com.prueba1.main.rest.Repos.MissionRepository;
import com.prueba1.main.rest.Validations.ActionResult;
import com.prueba1.main.rest.Validations.ValidationServices.MissionValidationService;

@Service
public class MissionService {	
	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired
	private MissionValidationService missionValidationService;
	
	public Mission getMissionById(Long missionId) {
		Mission mission = missionRepository.getById(missionId);
		ActionResult missionIsNullValidation = missionValidationService.missionIsNullValidation(mission);
		
		if (missionIsNullValidation.isExistError()) {
			throw new ResourceNotFoundException(missionIsNullValidation.getCompleteErrorMessages());
		}
		return mission;
	}
}
