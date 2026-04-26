package DTOs.Character;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddCharacterToMissionDTO {
	private Long playerId;
	private Long missionId;
	private Long characterId;
}
