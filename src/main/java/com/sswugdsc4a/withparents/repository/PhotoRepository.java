package com.sswugdsc4a.withparents.repository;

import com.sswugdsc4a.withparents.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query(value = "select * from photo where family_id = ?1 " +
            "limit 5 ;"
            , nativeQuery = true)
    List<Photo> getRecentPhotos(Long familyId);

}
