package ru.ob6.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmDto {

    private Long id;
    private String title;

    private String description;

    private String trailerUrl;

    private String posterName;

    private Boolean inRent;
}
