package com.sswugdsc4a.withparents.dto.dto.user;

import com.sswugdsc4a.withparents.dto.dto.ScheduleDTO;
import com.sswugdsc4a.withparents.dto.dto.medication.MedicationDTO;
import com.sswugdsc4a.withparents.dto.dto.photo.PhotoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HomeInfoDTO {

    private List<SimpleUserInfoDTO> userList;
    private List<MedicationDTO> todayMedicationList;
    private List<ScheduleDTO> todayScheduleList;
    private List<PhotoDTO> recentPhotoList;

}
