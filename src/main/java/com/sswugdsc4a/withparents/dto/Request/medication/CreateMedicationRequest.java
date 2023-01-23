package com.sswugdsc4a.withparents.dto.Request.medication;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class CreateMedicationRequest {

    private Long userId;
    private String description;
    private String dayOfTheWeekList;
    private LocalTime dosingTime;

}
