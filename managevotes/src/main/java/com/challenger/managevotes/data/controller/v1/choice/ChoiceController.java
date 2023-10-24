package com.challenger.managevotes.data.controller.v1.choice;

import com.challenger.managevotes.domain.dtos.choice.ChoiceDTO;
import com.challenger.managevotes.domain.entities.choice.Choice;
import com.challenger.managevotes.data.services.choice.ChoiceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("v1/vote")
@RestController
public class ChoiceController {
    private static final Logger logger = LoggerFactory.getLogger(ChoiceController.class);
    private final ChoiceServiceImpl votesService;
    private final ObjectMapper objectMapper;

    @PostMapping("/{idSchedule}/choice")
    public ResponseEntity votes(@PathVariable("idSchedule") Integer idSchedule, @RequestBody @Valid ChoiceDTO choice){
        votesService.vote(idSchedule, objectMapper.convertValue(choice, Choice.class));
        logger.info("Registered vote with successful!");
        return ResponseEntity.ok().build();
    }
}
