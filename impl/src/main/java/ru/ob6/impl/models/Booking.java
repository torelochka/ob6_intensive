package ru.ob6.impl.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Seat seat;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private User user;

    @OneToOne
    @JoinColumn(name = "seance_id", referencedColumnName = "id")
    private Seance seance;

}
