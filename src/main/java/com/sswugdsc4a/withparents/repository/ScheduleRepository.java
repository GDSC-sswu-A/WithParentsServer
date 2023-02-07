package com.sswugdsc4a.withparents.repository;

import com.sswugdsc4a.withparents.entity.Medication;
import com.sswugdsc4a.withparents.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUserId(Long userId);
}

