package com.challenger.managevotes.domain.repositories.sessionVotes;

import com.challenger.managevotes.domain.entities.Session.SessionVotes;
import com.challenger.managevotes.domain.entities.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ISessionRepository extends JpaRepository<SessionVotes, Integer> {
    Optional<SessionVotes> findBySchedule(Schedule schedule);
}
