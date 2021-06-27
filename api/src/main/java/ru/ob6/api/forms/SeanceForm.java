package ru.ob6.api.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeanceForm {

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Long FilmId;
}
