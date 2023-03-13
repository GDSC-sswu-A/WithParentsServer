package com.sswugdsc4a.withparents.dto.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationAndNicknameDTO {

    private Long userId;
    private String nickname;
    private String latitude;
    private String longitude;

}
