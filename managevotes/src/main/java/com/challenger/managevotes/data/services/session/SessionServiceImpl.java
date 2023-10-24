package com.challenger.managevotes.data.services.session;

import com.challenger.managevotes.domain.entities.Session.SessionVotes;
import com.challenger.managevotes.domain.entities.schedule.Schedule;
import com.challenger.managevotes.domain.repositories.schedule.ISchedulesRepository;
import com.challenger.managevotes.domain.repositories.sessionVotes.ISessionRepository;
import com.challenger.managevotes.core.exceptions.RolesException.MessageException;
import com.challenger.managevotes.core.exceptions.RolesException.RolesException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements ISessionService {
    @Value("${time.session.votes.seconds}")
    private long timeSessionDefault;

    private final ISchedulesRepository schedulesRepository;
    private final ISessionRepository sessionVotesRepository;

    @Override
    public Optional<Schedule> listScheduleById(Integer id) {
        return schedulesRepository.findById(id);
    }

    @Transactional
    public void starSessionVotes(Integer idSchedule, LocalDateTime closeDate){
        Schedule schedule = listScheduleById(idSchedule).orElseThrow(() -> new RolesException(MessageException.SCHEDULE_NOT_FOUND, HttpStatus.NOT_FOUND));
        if(Objects.requireNonNull(getSessionVotes(schedule)).isPresent()){
            throw new RolesException(MessageException.SESSION_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        createSessionVotes(schedule, closeDate);
    }

    private void createSessionVotes(Schedule schedule, LocalDateTime closeDate){
        SessionVotes sessionVotes = SessionVotes.builder()
                .openDate(LocalDateTime.now())
                .closeDate(closeDate(closeDate))
                .schedule(schedule)
                .build();

        sessionVotesRepository.save(sessionVotes);
    }

    private LocalDateTime closeDate(LocalDateTime closeDate){
        return closeDate == null ? LocalDateTime.now().plusSeconds(timeSessionDefault) : closeDate;
    }

    private Optional<SessionVotes> getSessionVotes(Schedule schedule){
        return sessionVotesRepository.findBySchedule(schedule);
    }

    public Long getTimeSessionDefault(){
        return timeSessionDefault;
    }
}
