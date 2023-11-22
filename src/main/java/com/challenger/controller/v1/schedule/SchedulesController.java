package com.challenger.controller.v1.schedule;

import com.challenger.dtos.schedule.ScheduleDTO;
import com.challenger.dtos.schedule.ScheduleResponseDTO;
import com.challenger.model.schedule.Schedule;
import com.challenger.services.schedule.ScheduleServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("v1/schedule")
@RequiredArgsConstructor
public class SchedulesController {

    private static final Logger logger = LoggerFactory.getLogger(SchedulesController.class);
    @Autowired
    private final ScheduleServiceImpl scheduleService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<ScheduleResponseDTO> listScheduleResult(){
        logger.info("Getting all schedules");
        return scheduleService.listAllSchedules().stream()
                .map(this::listScheduleResult)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> newSchedule(@RequestBody @Valid ScheduleDTO scheduleDTO){
        logger.info("Creating new Schedule");
        Schedule schedule = objectMapper.convertValue(scheduleDTO, Schedule.class);
        schedule = scheduleService.newSchedule(schedule);

        ResponseEntity.ok(objectMapper.convertValue(schedule, ScheduleResponseDTO.class));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
//
    private ScheduleResponseDTO listScheduleResult(Schedule schedule){
        ScheduleResponseDTO scheduleResponse = objectMapper.convertValue(schedule, ScheduleResponseDTO.class);
        scheduleResponse.setResult(scheduleService.result(schedule));

        return scheduleResponse;
    }
}
