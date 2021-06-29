package ru.ob6.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private UUID id;
    private String email;
    private String firstName;
    private String city;
    private Boolean isEmailConfirmed;
    private String role;
    private String password;
    private Set<BookingDto> bookings;
    private List<SeanceDto> seances;
}
