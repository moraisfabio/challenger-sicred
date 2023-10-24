package com.challenger.managevotes.data.services.schedule;

import com.challenger.managevotes.core.constants.RabbitmqConstans;
import com.challenger.managevotes.data.services.rabbitmq.RabbitMqService;
import com.challenger.managevotes.domain.entities.Session.SessionVotes;
import com.challenger.managevotes.domain.entities.schedule.Schedule;
import com.challenger.managevotes.domain.entities.choice.Choice;
import com.challenger.managevotes.domain.repositories.schedule.ISchedulesRepository;
import com.challenger.managevotes.domain.repositories.sessionVotes.ISessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Cacheable(value = "schedules", key = "#schedule.id")
public class ScheduleServiceImpl implements IScheduleService{

    private final ISchedulesRepository iSchedulesRepository;

    private final ISessionRepository iSessionRepository;

    @Autowired
    RabbitMqService rabbitMqService;

    @CacheEvict(value = "schedules", allEntries = true)
    @Transactional
    @Override
    public Schedule newSchedule(Schedule schedule) {
        iSchedulesRepository.save(schedule);
        return schedule;
    }

    @Cacheable(value = "schedules")
    @Override
    public List<Schedule> listAllSchedules() {
        return iSchedulesRepository.findAll();
    }

    @Override
    public Optional<Schedule> listScheduleById(Integer id) {
        return iSchedulesRepository.findById(id);
    }

    @Override
    public Optional<SessionVotes> listSessionVotes(Schedule schedule) {
        return iSessionRepository.findBySchedule(schedule);
    }
    public Map<String, Long> result(Schedule schedule) {

        Collection<Choice> votes = listSessionVotes(schedule).isPresent() ? listSessionVotes(schedule).get().getVotes() : new ArrayList<>();

        Map<String, Long> result = new HashMap<>();
        result.put("YES", votes.stream().filter(v -> v.getChoice().toString().equalsIgnoreCase("YES")).count());
        result.put("NO", votes.stream().filter(v -> v.getChoice().toString().equalsIgnoreCase("NO")).count());
        this.rabbitMqService.sendMessage(RabbitmqConstans.ROW_VOTE, result);
        return result;
    }
}
