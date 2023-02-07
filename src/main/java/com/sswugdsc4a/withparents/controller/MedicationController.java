package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.dto.request.medication.CreateMedicationRequest;
import com.sswugdsc4a.withparents.dto.dto.medication.MedicationDTO;
import com.sswugdsc4a.withparents.dto.request.medication.ModifyMedicationRequest;
import com.sswugdsc4a.withparents.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/api/medication/deleteMedication")
    public void deleteMedication(
            @RequestParam Long medicationId
    ){
        medicationService.deleteMedication(medicationId);
    }

    @GetMapping("/api/medication/getMedicationList")
    public List<MedicationDTO> getMedicationList(
            @RequestParam Long userId
    ){
        return medicationService.getMedicationList(userId);
    }

    @GetMapping("/api/medication/getTodayMedicationList")
    public List<MedicationDTO> getTodayMedicationList(
            @RequestParam Long userId
    ){
        return medicationService.getTodayMedicationList(userId);
    }

}
