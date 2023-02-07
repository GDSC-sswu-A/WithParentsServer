package com.sswugdsc4a.withparents.dto.dto.user;

import com.sswugdsc4a.withparents.entity.LocationInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocationInfoDTO {

    private Long userId;
    private String locationInfo;

    public LocationInfoDTO entityToDTO(LocationInfo e){
        return new LocationInfoDTO(
                e.getUserId(),
                e.getLocationInfo()
        );
    }

}
