package com.sswugdsc4a.withparents.repository;

import com.sswugdsc4a.withparents.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByFamilyId(Long familyId);

    @Query(value = "SELECT * from users " +
            "WHERE family_id = ?1 AND " +
            "is_parent = true", nativeQuery = true)
    List<User> getParents(Long familyId);
}
