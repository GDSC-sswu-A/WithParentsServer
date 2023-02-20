package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.dto.Request.schedule.CreateScheduleRequest;
import com.sswugdsc4a.withparents.dto.dto.schedule.ScheduleDTO;
import com.sswugdsc4a.withparents.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/api/schedule/createSchedule")
    public ScheduleDTO createSchedule(
            @RequestBody CreateScheduleRequest body
    ) {
        return scheduleService.createSchedule(
                body.getUserId(),
                body.getTitle(),
                body.getDate(),
                body.getTime()
        );
    }

    @GetMapping("/api/schedule/getScheduleList")
    public List<ScheduleDTO> getScheduleList(
            @RequestParam LocalDate date, Long userId
    ) {
        return scheduleService.getScheduleList(userId, date);
    }

    @DeleteMapping("/api/schedule/deleteSchedule")
    public void deleteSchedule(
            @RequestParam Long scheduleId
    ){
        scheduleService.deleteSchedule(scheduleId);
    }
};
