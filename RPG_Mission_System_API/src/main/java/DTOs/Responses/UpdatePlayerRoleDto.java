package DTOs.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePlayerRoleDto {
	private Long playerId;
	private String role;
}
