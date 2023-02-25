package com.sswugdsc4a.withparents.config;

import com.sswugdsc4a.withparents.filter.JwtAuthorizationFilter;
import com.sswugdsc4a.withparents.repository.LastApiCallTimeRepository;
import com.sswugdsc4a.withparents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PropertyConfig propertyConfig;
    private final UserRepository userRepository;
    private final LastApiCallTimeRepository lastApiCallTimeRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 필터 비활성화
        http.httpBasic().disable();
        http.csrf().disable();

        // 비연결상태 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // jwt 필터 추가
        http.addFilterAfter(
                new JwtAuthorizationFilter(userRepository, lastApiCallTimeRepository, propertyConfig),
                BasicAuthenticationFilter.class
        );

        // 접근권한 설정
        http.authorizeRequests().antMatchers(
                "/", "/api/auth/testLogin", "/api/auth/googleLogin"
        ).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .hasRole("USER");

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
