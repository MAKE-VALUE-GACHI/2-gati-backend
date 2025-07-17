package com.gati.hankki.config.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "swagger-info")
public class SwaggerProperties {
    private String version;
    private String title;
    private String description;
}
