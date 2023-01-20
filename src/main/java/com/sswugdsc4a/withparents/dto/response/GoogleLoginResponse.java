package com.sswugdsc4a.withparents.dto.response;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class GoogleLoginResponse {
    private String jwtToken;
    private Boolean isNewMember;
}
