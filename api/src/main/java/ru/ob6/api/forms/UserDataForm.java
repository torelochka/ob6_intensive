package ru.ob6.api.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ob6.api.dto.BookingDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDataForm {

    private String  email;

    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @NotBlank(message = "Город не может быть пустым")
    private String city;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,}$|^\\s*$", message = "Пароль должен содержать по крайней мере 1 латинскую букву, 1 цифру и быть не короче 6 символов")
    private String password;
}
