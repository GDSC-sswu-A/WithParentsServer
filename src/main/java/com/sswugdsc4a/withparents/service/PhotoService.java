package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.dto.dto.photo.PhotoDTO;
import com.sswugdsc4a.withparents.entity.Family;
import com.sswugdsc4a.withparents.entity.Photo;
import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.CustomException;
import com.sswugdsc4a.withparents.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public PhotoDTO modifyPhoto(
            Long photoId,
            String imageUrl,
            String description
    ){
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(()-> new CustomException("invalid photo id"));

        if (photoId == null){
            throw new CustomException("Please enter photoId");
        }

        if(!userService.areTheyAFamily(photo.getCreator().getId())){
            throw new CustomException("Family id is different");
        }
        if(imageUrl != null){
            photo.setImageUrl(imageUrl);
        }
        if(description != null){
            photo.setDescription(description);
        }
        return PhotoDTO.entityToDto(photo);
    }

    @Transactional
    public void deletePhoto(
            Long photoId
    ){
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(()-> new CustomException("invalid photo id"));

        if (photo.getCreator().getId() != userService.getUser().getId()){
            throw new CustomException("Only photo creator can be deleted");
        }
        photoRepository.delete(photo);
    }
    public List<PhotoDTO> getPhotoList(Long familyId) {

        User user = userService.getUser();

        if (userService.getUser().getFamily() == null){
            throw new CustomException("Family id does not exist");
        }
        return photoRepository.getRecentPhotos(userService.getUser().getFamily().getId())
                .stream()
                .map(e-> PhotoDTO.entityToDto(e))
                .collect(Collectors.toList());
    }

}



