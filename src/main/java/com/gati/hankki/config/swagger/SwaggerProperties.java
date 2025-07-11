package com.gati.hankki.config.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "swagger-info")
public class SwaggerProperties {
    private String version;
    private String title;
    private String description;
}
