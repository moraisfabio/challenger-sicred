package com.challenger.managevotes.domain.repositories;

import com.challenger.managevotes.domain.entities.Schedule;

import java.util.List;

public interface ISchedulesRepositories {
    void newSchedule(Schedule schedule);
    List<Schedule> findAllSchedules();
}
