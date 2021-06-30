package ru.ob6.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class YouTubeUrlValidator implements ConstraintValidator<YouTubeUrl, String> {

    @Override
    public void initialize(YouTubeUrl constraintAnnotation) {

    }

    @Override
    public boolean isValid(String youTubeUrl, ConstraintValidatorContext constraintValidatorContext) {
        return youTubeUrl != null && isYouTubeIFrameUrl(youTubeUrl);
    }

    private boolean isYouTubeIFrameUrl(String text) {
        if (text.contains("watch")) {
            return false;
        }
        else {
            return text.matches("^(https?://)?(www\\.youtube\\.com|youtu\\.?be)/.+$");
        }
    }
}
