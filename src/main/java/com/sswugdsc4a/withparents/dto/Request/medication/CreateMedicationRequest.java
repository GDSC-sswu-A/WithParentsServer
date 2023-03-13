package com.sswugdsc4a.withparents.dto.request.medication;

import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
public class CreateMedicationRequest {

    private Long userId;
    private String description;
    private String dayOfTheWeekList;
    private List<LocalTime> dosingTimeList;
    private Boolean notificationStatus;
}
