package com.challenger.managevotes.data.services.session;

import com.challenger.managevotes.domain.entities.schedule.Schedule;

import java.util.Optional;

public interface ISessionService {
    Optional<Schedule> listScheduleById(Integer id);
}
