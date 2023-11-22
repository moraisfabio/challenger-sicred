package com.challenger.services.choice;

import com.challenger.model.Session.SessionVotes;
import com.challenger.model.schedule.Schedule;

import java.util.Optional;

public interface IChoiceService {
    Optional<Schedule> listSchedule(Integer id);
    Optional<SessionVotes> listSessionVotes(Schedule schedule);
}
