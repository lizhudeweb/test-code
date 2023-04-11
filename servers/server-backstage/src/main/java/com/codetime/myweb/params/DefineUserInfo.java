package com.codetime.myweb.params;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "define.userinfo")
@Component
@Data
public class DefineUserInfo {

    private String username;
    private int age;
}
