package com.challenger.managevotes.data.services.choice;

import com.challenger.managevotes.domain.entities.Session.SessionVotes;
import com.challenger.managevotes.domain.entities.schedule.Schedule;

import java.util.Optional;

public interface IChoiceService {
    Optional<Schedule> listSchedule(Integer id);
    Optional<SessionVotes> listSessionVotes(Schedule schedule);
}
