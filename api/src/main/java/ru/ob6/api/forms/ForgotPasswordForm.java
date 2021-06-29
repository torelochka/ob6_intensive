package ru.ob6.api.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordForm {
    @Email(message = "Почта не является действительной")
    private String email;
    @Builder.Default
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,}$", message = "Пароль слишком простой")
    private String password = "";
    @Builder.Default
    private String passwordAgain = "";
}
