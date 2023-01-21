package com.sswugdsc4a.withparents.dto.dto.user;

import com.sswugdsc4a.withparents.entity.Family;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FamilyDTO {

    private Long id;
    private String password;
    private String creatorEmail;

    public static FamilyDTO entityToDTO(Family e){
        return new FamilyDTO(
                e.getId(),
                e.getPassword(),
                e.getCreator().getEmail()
        );
    }

}
