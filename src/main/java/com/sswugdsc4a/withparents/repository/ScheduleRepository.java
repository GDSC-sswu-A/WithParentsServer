package com.sswugdsc4a.withparents.repository;

import com.sswugdsc4a.withparents.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT * FROM schedule WHERE family_id = ?1 AND " +
            "YEAR(date) = ?2 AND MONTH(date) = ?3 ; "
            , nativeQuery = true)
    List<Schedule> getScheduleByYearAndMonth(Long familyId, int year, int month);

    @Query(value = "SELECT * FROM schedule WHERE family_id = ?1 AND " +
            "date = ?2 ; "
            , nativeQuery = true)
    List<Schedule> getTodaySchedule(Long familyId, LocalDate date);

}
