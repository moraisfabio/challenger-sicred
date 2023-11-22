package com.challenger.services.schedule;

import com.challenger.model.Session.SessionVotes;
import com.challenger.model.schedule.Schedule;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface IScheduleService {

    Schedule newSchedule(Schedule schedule);
    @Cacheable(value = "schedules")
    List<Schedule> listAllSchedules();
    Optional<Schedule> listScheduleById(Integer id);

    Optional<SessionVotes> listSessionVotes(Schedule schedule);

}
