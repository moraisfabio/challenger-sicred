package com.challenger.repositories.schedule;

import com.challenger.model.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ISchedulesRepository extends JpaRepository<Schedule, Integer> {
}
