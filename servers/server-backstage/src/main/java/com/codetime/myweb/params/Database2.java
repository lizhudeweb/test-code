package com.codetime.myweb.params;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = "classpath:config/db.properties")
@Component
@Data
public class Database2 {

    @Value("${database.name}")
    private String name;

    @Value("${database.version}")
    private String version;
}
