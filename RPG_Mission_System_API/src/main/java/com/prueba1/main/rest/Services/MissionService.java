package com.prueba1.main.rest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba1.main.rest.Models.Mission;
import com.prueba1.main.rest.Repos.MissionRepository;

import Exceptions.ResourceNotFoundException;

@Service
public class MissionService {

	@Autowired
	private MissionRepository missionRepository;
	
	public Mission getMissionById(Long missionId) {
		Mission mission = missionRepository.getById(missionId);
		if(mission == null) {
			throw new ResourceNotFoundException("Mission not found");
		}
		
		return mission;
	}
}
