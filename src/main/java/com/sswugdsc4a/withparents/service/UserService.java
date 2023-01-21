package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.dto.dto.user.FamilyDTO;
import com.sswugdsc4a.withparents.dto.dto.user.UserDTO;
import com.sswugdsc4a.withparents.entity.Family;
import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.custion_exceptions.InvalidValueException;
import com.sswugdsc4a.withparents.repository.FamilyRepository;
import com.sswugdsc4a.withparents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;

    public User getUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new InvalidValueException(userId, "존재하지 않는 user id입니다"));
    }

    public Family getFamily(Long id){
        return familyRepository.findById(id)
                .orElseThrow(() -> new InvalidValueException(id.toString(), "유효하지 않은 family id"));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidValueException(email, "해당 email을 가진 user가 존재하지 않음"));
    }

    public Boolean isExistingMember(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public UserDTO createUser(String email) {
        return UserDTO.entityToDTO(
                userRepository.save(
                        new User(
                                null,
                                email,
                                "nickname",
                                null,
                                "ROLE_USER",
                                null,
                                null
                        )
                )
        );
    }

    @Transactional
    public FamilyDTO createFamily(String password) {
        User creator = getUser();

        return FamilyDTO.entityToDTO(
                familyRepository.save(
                        new Family(
                                null,
                                password,
                                null,
                                creator
                        )
                )
        );
    }

    @Transactional
    public UserDTO modifyUserInfo(
            String nickname,
            Long familyId,
            Boolean isParent) {

        User user = getUser();

        if(nickname != null) {
            user.setNickname(nickname);
        }

        if(familyId != null) {
            user.setFamily(getFamily(familyId));
        }

        if(isParent != null) {
            user.setIsParent(isParent);
        }

        return UserDTO.entityToDTO(user);
    }
}
