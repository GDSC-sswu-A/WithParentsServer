package com.sswugdsc4a.withparents.dto.Request.schedule;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class CreateScheduleRequest {
    private Long userId;
    private String title;
    private LocalDate date;
    private LocalTime time;
}
