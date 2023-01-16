package com.sswugdsc4a.withparents.filter;

import com.sswugdsc4a.withparents.config.PropertyConfig;
import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.custion_exceptions.InvalidIdException;
import com.sswugdsc4a.withparents.exception.custion_exceptions.InvalidValueException;
import com.sswugdsc4a.withparents.exception.custion_exceptions.TokenDoesNotExistException;
import com.sswugdsc4a.withparents.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final PropertyConfig propertyConfig;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        String token = null;

        try{
            // 헤더에서 jwt_token 가져오기
            token = request.getHeader("jwt_token");

            if (token == null) {
                throw new TokenDoesNotExistException();
            }

            // jwt 토큰에서 user_id 가져오기
            String userId = (String) Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(propertyConfig.getJwtkey().getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("user_id");

            // userId로 user 객체 얻기
            Optional<User> user = userRepository.findByEmail(userId);

            if (user.isEmpty()) {
                throw new InvalidValueException(userId, "userId");
            }

            // Authenticaton 생성하고 SecurityContext에 설정하기
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.get(),
                    null,
                    user.get().getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (Exception e){

        }

        chain.doFilter(request, response);

    }

}
