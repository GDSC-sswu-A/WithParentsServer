package com.sswugdsc4a.withparents.dto.request.schedule;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class CreateScheduleRequest {
    private String title;
    private LocalDate date;
    private LocalTime time;
    private Boolean notificationStatus;
}
