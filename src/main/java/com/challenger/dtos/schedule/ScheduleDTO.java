package com.challenger.dtos.schedule;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@EqualsAndHashCode
@ToString
public class ScheduleDTO {
    @NotBlank(message = "description is required")
    private String description;
}
