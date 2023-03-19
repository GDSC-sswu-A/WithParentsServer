package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.dto.dto.ScheduleDTO;
import com.sswugdsc4a.withparents.entity.Schedule;
import com.sswugdsc4a.withparents.exception.CustomException;
import com.sswugdsc4a.withparents.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Transactional
    public ScheduleDTO createSchedule(
            String title,
            LocalDate date,
            LocalTime time,
            Boolean notificationStatus
    ) {
        if (userService.getUser().getFamily() == null) {
            throw new CustomException("Family id does not exist");
        }
        if (title.isEmpty()){
            throw new CustomException("Please enter a title");
        }
        if (date == null){
            throw new DateTimeException("Please enter the date");
        }
        if(time == null){
            throw new DateTimeException("Please enter the time");
        }
        return ScheduleDTO.entityToDTO(
                scheduleRepository.save(
                        new Schedule(
                                null,
                                userService.getUser().getFamily(),
                                userService.getUser(),
                                null,
                                title,
                                date,
                                time,
                                notificationStatus,
                                null
                        )

                )

        );
    }

    @Transactional
    public ScheduleDTO modifySchedule(
            Long scheduleId,
            String title,
            LocalDate date,
            LocalTime time,
            Boolean notificationStatus
    ){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException("invalid schedule id"));

        if (!userService.areTheyAFamily(schedule.getCreator().getId())){
            throw new CustomException("Family id is different");
        }
        if(title != null){
            schedule.setTitle(title);
        }
        if(date != null){
            schedule.setDate(date);
        }
        if(time != null){
            schedule.setTime(time);
        }
        if(notificationStatus != null){
            schedule.setNotificationState(notificationStatus);
        }
        return ScheduleDTO.entityToDTO(schedule);

    }

    @Transactional
    public void deleteSchedule(
            Long scheduleId
    ){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()->new CustomException("invalid schedule id"));

        if (schedule.getCreator().getId() != userService.getUser().getId()) {
            throw new CustomException("Only schedule creator can be deleted");
        }

        scheduleRepository.delete(schedule);
    }

    public List<ScheduleDTO> getScheduleList(
            int year,
            int month
    ) {

        if (userService.getUser().getFamily() == null) {
            throw new CustomException("Family id does not exist");
        }

        return scheduleRepository.getScheduleByYearAndMonth(
                userService.getUser().getFamily().getId(), year, month)
                .stream()
                .map(e -> {return ScheduleDTO.entityToDTO(e);})
                .collect(Collectors.toList());

    }

    public List<ScheduleDTO> getTodayScheduleList(){

        if (userService.getUser().getFamily() == null) {
            throw new CustomException("Family id does not exist");
        }

        return scheduleRepository.getTodaySchedule(userService.getUser().getFamily().getId(), LocalDate.now())
                .stream()
                .map(e -> {return ScheduleDTO.entityToDTO(e);})
                .collect(Collectors.toList());

    }
}
