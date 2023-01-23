package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.dto.dto.medication.MedicationDTO;
import com.sswugdsc4a.withparents.entity.Medication;
import com.sswugdsc4a.withparents.exception.CustomException;
import com.sswugdsc4a.withparents.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final UserService userService;

    @Transactional
    public MedicationDTO createMedication(
            Long userId,
            String description,
            String dayOfTheWeekList,
            LocalTime dosingTime
    ) {

        if (!userService.areTheyAFamily(userId)) {
            throw new CustomException("Family id is different");
        }

        return MedicationDTO.entityToDto(
                medicationRepository.save(
                        new Medication(
                                null,
                                userService.getUserById(userId),
                                description,
                                dayOfTheWeekList,
                                dosingTime

                        )
                )
        );


    }
}
