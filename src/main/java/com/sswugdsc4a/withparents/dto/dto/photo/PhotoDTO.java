package com.sswugdsc4a.withparents.dto.dto.photo;

import com.sswugdsc4a.withparents.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PhotoDTO {

    private Long id;
    private Long creatorId;
    private String creatorName;
    private String imageUrl;
    private String description;
    private LocalDateTime regDate;

    public static PhotoDTO entityToDto(Photo e){
        return new PhotoDTO(
                e.getId(),
                e.getCreator().getId(),
                e.getCreator().getNickname(),
                e.getImageUrl(),
                e.getDescription(),
                e.getRegDate()
        );
    }

}
