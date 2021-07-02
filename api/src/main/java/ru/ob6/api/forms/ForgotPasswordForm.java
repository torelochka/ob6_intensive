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
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,}$", message = "Пароль должен содержать не менее 6 символов, как минимум одну строчную букву и одну цифру")
    private String password = "";
    @Builder.Default
    private String passwordAgain = "";
}
