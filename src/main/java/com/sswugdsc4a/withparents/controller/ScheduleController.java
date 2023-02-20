package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.dto.dto.ScheduleDTO;
import com.sswugdsc4a.withparents.dto.request.schedule.CreateScheduleRequest;
import com.sswugdsc4a.withparents.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
                body.getTitle(),
                body.getDate(),
                body.getTime(),
                body.getNotificationStatus()
        );
    }

    @GetMapping("/api/schedule/getScheduleList")
    public List<ScheduleDTO> getScheduleList(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return scheduleService.getScheduleList(year, month);
    }

    @GetMapping("/api/schedule/getTodayScheduleList")
    public List<ScheduleDTO> getTodayScheduleList(
    ) {
        return scheduleService.getTodayScheduleList();
    }

    @DeleteMapping("/api/schedule/deleteSchedule")
    public void deleteSchedule(
            @RequestParam Long scheduleId
    ){
        scheduleService.deleteSchedule(scheduleId);
    }
};

