package com.challenger.managevotes.domain.dtos.schedule;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@EqualsAndHashCode
@ToString
public class ScheduleResponseDTO implements Serializable {
    private String id;
    private String description;
    private Map<String , Long> result;
}
