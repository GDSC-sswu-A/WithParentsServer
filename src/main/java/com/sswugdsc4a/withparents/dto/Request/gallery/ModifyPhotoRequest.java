package com.sswugdsc4a.withparents.dto.request.gallery;

import lombok.Getter;

@Getter
public class ModifyPhotoRequest {
    private Long photoId;
    private String imageUrl;
    private String description;
}
