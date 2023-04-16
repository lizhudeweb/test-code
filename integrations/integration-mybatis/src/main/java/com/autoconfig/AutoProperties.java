package com.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auto")
@Data
public class AutoProperties {

    private String name;

    private String message;
}