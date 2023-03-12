package com.sswugdsc4a.withparents.controller;

import com.sswugdsc4a.withparents.dto.dto.photo.PhotoDTO;
import com.sswugdsc4a.withparents.dto.request.gallery.UploadPhotoRequest;
import com.sswugdsc4a.withparents.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/api/gallery/uploadPhoto")
    public PhotoDTO uploadPhoto(
            @RequestBody UploadPhotoRequest body
    ){
        return photoService.uploadPhoto(
                body.getImageUrl(),
                body.getDescription()
        );
    }
    @DeleteMapping("/api/gallery/deletePhoto")
    public void deletePhoto(
            @RequestParam Long photoId
    ){
        photoService.deletePhoto(photoId);
    }

}
