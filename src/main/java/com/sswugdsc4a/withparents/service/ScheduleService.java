package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.dto.dto.schedule.ScheduleDTO;
import com.sswugdsc4a.withparents.entity.Schedule;
import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.CustomException;
import com.sswugdsc4a.withparents.repository.ScheduleRepository;
import com.sswugdsc4a.withparents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository UserRepository;
    private final UserService userService;

    @Transactional
    public ScheduleDTO createSchedule(
            Long userId,
            String title,
            LocalDate date,
            LocalTime time
    ) {
        if (!userService.areTheyAFamily(userId)) {
            throw new CustomException("Family id is different");
        }

        return ScheduleDTO.entityToDTO(
                scheduleRepository.save(
                        new Schedule(
                                null,
                                userService.getUserById(userId),
                                null,
                                title,
                                date,
                                time
                        )

                )
        );

    }

    public List<ScheduleDTO> getScheduleList(Long userId){
        User user = userService.getUserById(userId);
        if(!userService.areTheyAFamily(userId)) {
            throw new CustomException("Family id is different");
        }
        return scheduleRepository.findAllByUserId(userId)
                .stream()
                .map(e -> ScheduleDTO.entityToDTO(e))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSchedule(
            Long scheduleId
    ){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()->new CustomException("invalid schedule id"));
        if (!userService.areTheyAFamily(schedule.getUser().getId())){
            throw new CustomException("Family id is different");
        }
        scheduleRepository.delete(schedule);
    }

//    public List<ScheduleDTO> getTodayScheduleList(Long userId){
//        User user = UserRepository.findById(userId)
//                .orElseThrow(()->new IllegalArgumentException());
//    }
//    public List<ScheduleDTO> getTodayScheduleList(Long userId){
//        User user = userService.getUserById(userId);
//
//        if(!userService.areTheyAFamily(userId)){
//            throw new CustomException("Family id is different");
//        }
//        int dayOfWeekNumber = switch (Calendar.getInstance().get(Calendar.D)){
//            case 1 -> 6;
//            case 2 -> 0;
//            case 3 -> 1;
//            case 4 -> 2;
//            case 5 -> 3;
//            case 6 -> 4;
//            case 7 -> 5;
//            default -> 8;
//        };
//        return scheduleRepository.findAllByUserId(userId)
//                .stream()
//                .filter(e -> {
//                    return e.get
//                })
    }

