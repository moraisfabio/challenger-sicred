package com.challenger.managevotes.domain.dtos.choice;

import com.challenger.managevotes.domain.entities.choice.EChoice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class ChoiceDTO {
    private String cpfUser;
    private EChoice choice;
}
