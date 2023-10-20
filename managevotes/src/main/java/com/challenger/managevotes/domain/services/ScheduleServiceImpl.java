package com.challenger.managevotes.domain.services;

import com.challenger.managevotes.domain.dtos.ScheduleDTO;
import com.challenger.managevotes.domain.entities.Schedule;
import com.challenger.managevotes.domain.repositories.ISchedulesRepositories;

import java.util.List;
import java.util.UUID;

public class ScheduleServiceImpl implements IScheduleService{
    private final ISchedulesRepositories schedulesRepositories;

    public ScheduleServiceImpl(ISchedulesRepositories schedulesRepositories){
        this.schedulesRepositories = schedulesRepositories;
    }

    @Override
    public String createNewSchedule(ScheduleDTO scheduleDTO){
        final Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setDescription(scheduleDTO.getDescription());

        schedulesRepositories.newSchedule(schedule);

        return schedule.getDescription();
    }

    @Override
    public List<Schedule> listSchedules(){
        return schedulesRepositories.findAllSchedules();
    }
}
