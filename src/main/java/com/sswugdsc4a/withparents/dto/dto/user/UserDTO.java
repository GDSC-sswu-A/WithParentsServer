package com.sswugdsc4a.withparents.dto.dto.user;

import com.sswugdsc4a.withparents.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String nickname;
    private Long familyId;
    private String familyPassword;
    private Boolean isParent;

    public static UserDTO entityToDTO(User e){
        return new UserDTO(
                e.getEmail(),
                e.getNickname(),
                e.getFamily() == null ? null : e.getFamily().getId(),
                e.getFamily() == null ? null : e.getFamily().getPassword(),
                e.getIsParent()
        );
    }
}
