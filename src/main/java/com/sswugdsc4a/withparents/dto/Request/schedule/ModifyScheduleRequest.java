package com.sswugdsc4a.withparents.dto.Request.schedule;


import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ModifyScheduleRequest {

    private Long scheduleId;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private Boolean notificationStatus;

}
