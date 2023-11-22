package com.challenger.repositories.sessionVotes;

import com.challenger.model.Session.SessionVotes;
import com.challenger.model.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ISessionRepository extends JpaRepository<SessionVotes, Integer> {
    Optional<SessionVotes> findBySchedule(Schedule schedule);
}
