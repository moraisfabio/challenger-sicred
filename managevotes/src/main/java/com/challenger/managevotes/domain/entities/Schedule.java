package com.challenger.managevotes.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "schedule")
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;

    public Schedule() {}
}
