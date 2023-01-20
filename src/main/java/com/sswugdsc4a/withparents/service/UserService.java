package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.custion_exceptions.InvalidValueException;
import com.sswugdsc4a.withparents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidValueException(email, "해당 email을 가진 user가 존재하지 않음"));
    }

    public Boolean isExistingMember(String email){
        return userRepository.findByEmail(email).isPresent();
    }


    public void createUser(String email, String nickname) {
        userRepository.save(new User(
                null,
                email,
                nickname,
                null,
                null
        ));
    }
}
