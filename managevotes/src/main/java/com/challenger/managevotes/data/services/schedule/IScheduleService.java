package com.challenger.managevotes.data.services.schedule;

import com.challenger.managevotes.domain.entities.Session.SessionVotes;
import com.challenger.managevotes.domain.entities.schedule.Schedule;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IScheduleService {

    Schedule newSchedule(Schedule schedule);
    @Cacheable(value = "schedules")
    List<Schedule> listAllSchedules();
    Optional<Schedule> listScheduleById(Integer id);

    Optional<SessionVotes> listSessionVotes(Schedule schedule);

}
