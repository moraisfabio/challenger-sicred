package com.challenger.services.session;

import com.challenger.model.schedule.Schedule;

import java.util.Optional;

public interface ISessionService {
    Optional<Schedule> listScheduleById(Integer id);
}
