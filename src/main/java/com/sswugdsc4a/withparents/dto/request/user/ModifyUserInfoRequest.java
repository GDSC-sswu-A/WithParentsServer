package com.sswugdsc4a.withparents.dto.request.user;

import lombok.Getter;

@Getter
public class ModifyUserInfoRequest {

    private String nickname;
    private Long familyId;
    private String familyPassword;
    private Boolean isParent;

}
