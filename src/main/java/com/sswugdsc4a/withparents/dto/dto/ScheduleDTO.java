package com.sswugdsc4a.withparents.dto.dto;

import com.sswugdsc4a.withparents.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleDTO {

    private Long id;
    private Long creatorId;
    private String nickname;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private Boolean notificationStatus;

    public static ScheduleDTO entityToDTO(Schedule e){
        return new ScheduleDTO(
                e.getId(),
                e.getCreator().getId(),
                e.getCreator().getNickname(),
                e.getTitle(),
                e.getDate(),
                e.getTime(),
                e.getNotificationState()
        );
    }
}