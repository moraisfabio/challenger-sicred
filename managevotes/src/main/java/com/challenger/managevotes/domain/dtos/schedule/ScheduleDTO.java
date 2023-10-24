package com.challenger.managevotes.domain.dtos.schedule;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Map;
@Data
@EqualsAndHashCode
@ToString
public class ScheduleDTO {
    @NotBlank(message = "description is required")
    private String description;
}
