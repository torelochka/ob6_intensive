package ru.ob6.impl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Type(type = "text")
    private String description;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "poster_name")
    private String posterName;

    @Column(name = "in_rent")
    @Builder.Default
    private Boolean inRent = true;
}
