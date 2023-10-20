package com.challenger.managevotes.domain.services;

import com.challenger.managevotes.domain.dtos.ScheduleDTO;
import com.challenger.managevotes.domain.entities.Schedule;

import java.util.List;

public interface IScheduleService {
    String createNewSchedule(ScheduleDTO scheduleDTO);
    List<Schedule> listSchedules();
}
