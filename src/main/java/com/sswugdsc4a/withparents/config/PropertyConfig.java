package com.sswugdsc4a.withparents.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PropertyConfig {

    @Value("${jwtkey}")
    private String jwtkey;

    @Value("${googleClientId1}")
    private String googleClientId1;

    @Value("${googleClientId2}")
    private String googleClientId2;
}
