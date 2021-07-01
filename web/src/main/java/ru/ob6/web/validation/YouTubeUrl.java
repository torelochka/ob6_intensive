package ru.ob6.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YouTubeUrlValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface YouTubeUrl {
    String message() default "Ссылка некорректна";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
