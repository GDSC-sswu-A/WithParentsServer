package com.sswugdsc4a.withparents.dto.dto.medication;

import com.sswugdsc4a.withparents.entity.Medication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class MedicationDTO {

    private Long id;
    private Long userId;
    private String description;
    private String dayOfTheWeekList;
    private LocalTime dosingTime;

    public static MedicationDTO entityToDto(Medication e){
        return new MedicationDTO(
                e.getId(),
                e.getUser().getId(),
                e.getDescription(),
                e.getDayOfTheWeekList(),
                e.getDosingTime()
        );
    }

}