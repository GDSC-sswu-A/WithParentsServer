package com.sswugdsc4a.withparents.dto.dto.user;

import com.sswugdsc4a.withparents.entity.LocationInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LocationInfoDTO {

    private Long userId;
    private LocalDateTime lastModifiedDate;
    private String latitude;
    private String longitude;

    public static LocationInfoDTO entityToDTO(LocationInfo e){
        return new LocationInfoDTO(
                e.getUserId(),
                e.getLastModifiedDate(),
                e.getLatitude(),
                e.getLongitude()
        );
    }

}
