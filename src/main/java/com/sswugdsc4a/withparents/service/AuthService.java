package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.custion_exceptions.InvalidIdException;
import com.sswugdsc4a.withparents.exception.custion_exceptions.InvalidValueException;
import com.sswugdsc4a.withparents.repository.UserRepository;
import com.sswugdsc4a.withparents.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String testLogin(String email, String pw) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidValueException(email, "email"));

        if (!user.getPassword().equals(pw)) {
            throw new InvalidValueException(pw, "비밀번호");
        }

        return jwtUtil.createJwtToken(user.getEmail());

    }

}
