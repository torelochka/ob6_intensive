package ru.ob6.api.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpForm {

    @Email(message = "Почта не является действительной")
    private String email;
    @NotBlank(message = "Город не может быть пустым")
    private String city;
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,}$", message = "Пароль должен содержать не менее 6 символов, как минимум одну строчную букву и одну цифру")
    private String password;
    private String passwordAgain;
}
