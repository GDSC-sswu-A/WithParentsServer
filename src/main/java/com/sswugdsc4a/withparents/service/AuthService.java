package com.sswugdsc4a.withparents.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.sswugdsc4a.withparents.config.PropertyConfig;
import com.sswugdsc4a.withparents.dto.response.GoogleLoginResponse;
import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.custion_exceptions.InvalidValueException;
import com.sswugdsc4a.withparents.repository.UserRepository;
import com.sswugdsc4a.withparents.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PropertyConfig propertyConfig;
    private final JwtUtil jwtUtil;


    public String testLogin(String email, String pw) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidValueException(email, "email"));

        if (!user.getPassword().equals(pw)) {
            throw new InvalidValueException(pw, "비밀번호");
        }

        return jwtUtil.createJwtToken(user.getEmail());

    }

    public GoogleLoginResponse googleLogin(String access_token)
            throws GeneralSecurityException, IOException {

        // 토큰 무결성 인증
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(transport, jsonFactory)
                .setAudience(Arrays.asList(
                        propertyConfig.getGoogleClientId1(),
                        propertyConfig.getGoogleClientId2()
                        ))
                .build();

        GoogleIdToken idToken = verifier.verify(access_token);

        if(idToken == null){
            throw new InvalidValueException(access_token, "유효하지 않은 access token");
        }

        // 토큰에서 email 추출
        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();

        String jwt = jwtUtil.createJwtToken(email);

        // 신규회원이면 createUser
        if (userService.isExistingMember(email)) {
            userService.createUser(email, "닉네임");
            return new GoogleLoginResponse(jwt, true);
        }

        return new GoogleLoginResponse(jwt, false);
    }
}