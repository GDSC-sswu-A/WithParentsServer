package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.dto.dto.photo.PhotoDTO;
import com.sswugdsc4a.withparents.entity.Photo;
import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.CustomException;
import com.sswugdsc4a.withparents.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                        imageUrl,
                        description,
                        null
                ))
        );
    }
}
