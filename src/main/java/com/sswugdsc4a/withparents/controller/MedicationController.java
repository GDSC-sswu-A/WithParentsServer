package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

}
