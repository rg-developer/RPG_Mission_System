package DTOs.Responses;

import java.time.LocalDate;
import com.prueba1.main.rest.Models.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultQuestLogResponse {
	private Long id;
	private Long characterId;
	private Long missionId;
	private Status status;
	private Boolean failed;
	private LocalDate startedAt;
	private LocalDate completedAt;
}
