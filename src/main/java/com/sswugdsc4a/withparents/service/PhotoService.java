package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.dto.dto.photo.PhotoDTO;
import com.sswugdsc4a.withparents.entity.Photo;
import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.CustomException;
import com.sswugdsc4a.withparents.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final UserService userService;

    public PhotoDTO uploadPhoto(
            String imageUrl,
            String description
    ){
        User user = userService.getUser();

        if (userService.getUser().getFamily() == null) {
            throw new CustomException("Family id does not exist");
        }

        return PhotoDTO.entityToDto(
                photoRepository.save(new Photo(
                        null,
                        user,
                        user.getFamily(),
                        imageUrl,
                        description,
                        null
                ))
        );
    }

    @Transactional
    public PhotoDTO modifyPhoto(Long photoId, String imageUrl, String description) {

        if (photoId == null) {
            throw new CustomException("photoId is required");
        }

        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new CustomException("invalid photo id"));

        if (photo.getCreator().getId() != userService.getUser().getId()) {
            throw new CustomException("Can only be deleted by the creator");
        }

        if (imageUrl != null) {
            photo.setImageUrl(imageUrl);
        }

        if (description != null) {
            photo.setDescription(description);
        }

        return PhotoDTO.entityToDto(photo);

    }
}
