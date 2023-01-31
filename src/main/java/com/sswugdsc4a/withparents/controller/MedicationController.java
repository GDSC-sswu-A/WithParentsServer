package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.dto.Request.medication.CreateMedicationRequest;
import com.sswugdsc4a.withparents.dto.dto.medication.MedicationDTO;
import com.sswugdsc4a.withparents.dto.request.medication.ModifyMedicationRequest;
import com.sswugdsc4a.withparents.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping("/api/medication/createMedication")
    public MedicationDTO createMedication(
            @RequestBody CreateMedicationRequest body
    ){
        return medicationService.createMedication(
                body.getUserId(),
                body.getDescription(),
                body.getDayOfTheWeekList(),
                body.getDosingTime()
        );
    }

    @PatchMapping("/api/medication/modifyMedication")
    public MedicationDTO modifyMedication(
            @RequestBody ModifyMedicationRequest body
    ){
        return medicationService.modifyMedication(
                body.getMedicationId(),
                body.getDescription(),
                body.getDayOfTheWeekList(),
                body.getDosingTime()
        );
    }

}
