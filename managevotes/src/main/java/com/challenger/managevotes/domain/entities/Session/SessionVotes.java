package com.challenger.managevotes.domain.entities.Session;

import com.challenger.managevotes.domain.entities.schedule.Schedule;
import com.challenger.managevotes.domain.entities.choice.Choice;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity(name = "session_vote")
@Table(name = "session_vote")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class SessionVotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime openDate;
    private LocalDateTime closeDate;

    @OneToOne
    @JoinColumn(name = "id_schedule")
    private Schedule schedule;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sessionVotes", cascade = CascadeType.ALL)
    private Collection<Choice> votes = new LinkedHashSet<Choice>();
}
