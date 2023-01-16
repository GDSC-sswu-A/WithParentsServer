package com.sswugdsc4a.withparents.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    @Value("${jwtkey}")
    private String jwtkey;

}
