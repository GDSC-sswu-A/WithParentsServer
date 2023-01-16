package com.sswugdsc4a.withparents.util;

import com.sswugdsc4a.withparents.config.PropertyConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final PropertyConfig propertyConfig;

    public String createJwtToken(String userId) {

        Map<String, Object> jwtHeader = new HashMap<>();
        jwtHeader.put("typ", "JWT");
        jwtHeader.put("alg", "HS256");

        Map<String, Object> jwtPayload = new HashMap<>();
        jwtPayload.put("user_id", userId);

        Long expiredTime = 1000 * 60L * 60L * 24L * 7L; // 7일
        Date date = new Date();
        date.setTime(date.getTime() + expiredTime);

        Key key = Keys.hmacShaKeyFor(propertyConfig.getJwtkey().getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setHeader(jwtHeader)
                .setClaims(jwtPayload)
                .setExpiration(date)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

}
