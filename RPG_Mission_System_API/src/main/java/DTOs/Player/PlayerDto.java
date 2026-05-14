package DTOs.Player;

import java.time.LocalDate;

import com.prueba1.main.rest.Models.Player.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
	private Long id;
    private String usurname;
    private String email;
    private String password;
    private Role role;
    private LocalDate createdAt;
}
