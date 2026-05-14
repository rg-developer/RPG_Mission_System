package DTOs.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SetPlayerRoleDto {
	private Long playerId;
	private String role;
}
