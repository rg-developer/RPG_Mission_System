package DTOs.Responses;

import java.util.List;

import com.prueba1.main.rest.Models.Character;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultPlayerResponse {
	private Long id;
	private String usurname;
	private List <Character> characters;
}
