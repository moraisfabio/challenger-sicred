package com.challenger.managevotes.domain.repositories.schedule;

import com.challenger.managevotes.domain.entities.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ISchedulesRepository extends JpaRepository<Schedule, Integer> {
}
