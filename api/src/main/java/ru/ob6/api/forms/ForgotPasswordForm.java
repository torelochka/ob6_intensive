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
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,}$", message = "Пароль должен содержать по крайней мере 1 латинскую букву, 1 цифру и быть не короче 6 символов")
    private String password = "";
    @Builder.Default
    private String passwordAgain = "";
}
