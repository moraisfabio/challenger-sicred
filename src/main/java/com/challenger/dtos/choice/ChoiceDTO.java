package com.challenger.dtos.choice;

import com.challenger.model.choice.EChoice;
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
