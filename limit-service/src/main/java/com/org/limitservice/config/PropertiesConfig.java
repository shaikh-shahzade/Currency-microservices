package com.org.limitservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("limit-service")
@Configuration
@Getter
@Setter
public class PropertiesConfig {
    private Integer minimum;
    private Integer maximum;

}
