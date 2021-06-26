package ru.ob6.web.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmForm {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String trailerUrl;

    private MultipartFile posterName;
}
