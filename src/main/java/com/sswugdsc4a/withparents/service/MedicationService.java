package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.dto.dto.medication.MedicationDTO;
import com.sswugdsc4a.withparents.entity.Medication;
import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.CustomException;
import com.sswugdsc4a.withparents.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public MedicationDTO modifyMedication(
            Long medicationId,
            String description,
            String dayOfTheWeekList,
            LocalTime dosingTime
    ) {

        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new CustomException("invalid medication id"));


        if (!userService.areTheyAFamily(medication.getUser().getId())) {
            throw new CustomException("Family id is different");
        }

        if (description != null) {
            medication.setDescription(description);
        }

        if (dayOfTheWeekList != null) {
            medication.setDayOfTheWeekList(dayOfTheWeekList);
        }

        if (dosingTime != null) {
            medication.setDosingTime(dosingTime);
        }

        return MedicationDTO.entityToDto(medication);

    }

    @Transactional
    public void deleteMedication(
            Long medicationId
    ) {

        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new CustomException("invalid medication id"));

        if (!userService.areTheyAFamily(medication.getUser().getId())) {
            throw new CustomException("Family id is different");
        }

        medicationRepository.delete(medication);
    }

    public List<MedicationDTO> getMedicationList(Long userId) {

        User user = userService.getUserById(userId);

        if (!userService.areTheyAFamily(userId)) {
            throw new CustomException("Family id is different");
        }

        return medicationRepository.findAllByUserId(userId)
                .stream()
                .map(e -> MedicationDTO.entityToDto(e))
                .collect(Collectors.toList());

    }

    public List<MedicationDTO> getTodayMedicationList(Long userId) {

        User user = userService.getUserById(userId);

        if (!userService.areTheyAFamily(userId)) {
            throw new CustomException("Family id is different");
        }

        int dayOfWeekNumber = switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            case 1 -> 6; // sun
            case 2 -> 0; // mon
            case 3 -> 1; // tue
            case 4 -> 2; // wed
            case 5 -> 3; // thu
            case 6 -> 4; // fri
            case 7 -> 5; // sat
            default -> 8; // ???
        };

        return medicationRepository.findAllByUserId(userId)
                .stream()
                .filter(e -> {
                    return e.getDayOfTheWeekList().charAt(dayOfWeekNumber) == '1';
                })
                .map(e -> MedicationDTO.entityToDto(e))
                .collect(Collectors.toList());

    }

}
