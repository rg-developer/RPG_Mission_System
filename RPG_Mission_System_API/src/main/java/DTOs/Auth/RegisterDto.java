package DTOs.Auth;

import java.time.LocalDate;

import com.prueba1.main.rest.Models.Player.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDto {

	private String usurname;
	private String email;
	private String password;
}
