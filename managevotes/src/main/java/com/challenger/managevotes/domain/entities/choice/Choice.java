package com.challenger.managevotes.domain.entities.choice;

import com.challenger.managevotes.domain.entities.Session.SessionVotes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "votes")
@Table(name = "votes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Choice {
    @NotNull(message = "CPF is required.")
    @Id
    private String cpfUser;

    @NotNull(message = "The Chosen is required, follow the instructions: YES/NO")
    @Enumerated(EnumType.STRING)
    private EChoice choice;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "id_schedule")
    private SessionVotes sessionVotes;
}
