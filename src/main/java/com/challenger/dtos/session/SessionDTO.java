package com.challenger.dtos.session;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
@EqualsAndHashCode
public class SessionDTO {
    private LocalDateTime dateTime;
}
