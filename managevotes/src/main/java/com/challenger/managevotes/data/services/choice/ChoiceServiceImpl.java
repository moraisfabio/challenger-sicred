package com.challenger.managevotes.data.services.choice;

import com.challenger.managevotes.domain.entities.Session.SessionVotes;
import com.challenger.managevotes.domain.entities.schedule.Schedule;
import com.challenger.managevotes.domain.entities.choice.Choice;
import com.challenger.managevotes.domain.repositories.schedule.ISchedulesRepository;
import com.challenger.managevotes.domain.repositories.sessionVotes.ISessionRepository;
import com.challenger.managevotes.domain.repositories.choice.IChoiceRepository;
import com.challenger.managevotes.core.exceptions.RolesException.MessageException;
import com.challenger.managevotes.core.exceptions.RolesException.RolesException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChoiceServiceImpl implements IChoiceService {
    @Autowired
    ISchedulesRepository schedulesRepository;
    @Autowired
    ISessionRepository sessionRepository;
    @Autowired
    IChoiceRepository votesRepository;

    public Optional<Schedule> listSchedule(Integer id){
        return schedulesRepository.findById(id);
    }

    public Optional<SessionVotes> listSessionVotes(Schedule schedule){
        return sessionRepository.findBySchedule(schedule);
    }

    @Transactional
    public void vote(Integer idSchedule, Choice choice) {
        SessionVotes sessionVotes = listSessionVotes(listSchedule(idSchedule)
                .orElseThrow(() -> new RolesException(MessageException.SCHEDULE_NOT_FOUND, HttpStatus.NOT_FOUND)))
                .orElseThrow(() -> new RolesException(MessageException.SESSION_NOT_FOUND, HttpStatus.NOT_FOUND));

        if (LocalDateTime.now().isAfter(sessionVotes.getCloseDate())) {
            throw new RolesException(MessageException.CLOSED_SESSION, HttpStatus.BAD_REQUEST);
        }

        choice.setSessionVotes(sessionVotes);
        choice.setDateTime(LocalDateTime.now());

        if(votesRepository.existsBySessionVotesAndCpfUser(sessionVotes, choice.getCpfUser())) {
            throw new RolesException(MessageException.REGISTERED_VOTE, HttpStatus.BAD_REQUEST);
        }

        votesRepository.save(choice);
    }

}
