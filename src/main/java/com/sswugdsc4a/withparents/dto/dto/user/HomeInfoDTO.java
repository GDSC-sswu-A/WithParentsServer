package com.sswugdsc4a.withparents.dto.dto.user;

import com.sswugdsc4a.withparents.dto.dto.medication.MedicationDTO;
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
    // TODO: 오늘의 일정, 최신 포스팅 추가

}
