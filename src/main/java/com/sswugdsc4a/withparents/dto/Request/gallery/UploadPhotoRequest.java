package com.sswugdsc4a.withparents.dto.request.gallery;

import lombok.Getter;

@Getter
public class UploadPhotoRequest {
    private Long familyId;
    private String imageUrl;
    private String description;

}
