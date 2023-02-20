package com.sswugdsc4a.withparents.dto.dto.schedule;

import com.sswugdsc4a.withparents.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.management.Notification;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor

public class ScheduleDTO {
    private Long id;
    private Long userId;
    private String title;
    private LocalDate Date;
    private LocalTime Time;

    public static ScheduleDTO entityToDTO(Schedule e){
        return new ScheduleDTO(
                e.getId(),
                e.getUser().getId(),
                e.getTitle(),
                e.getDate(),
                e.getTime()
            );
        }
    }



