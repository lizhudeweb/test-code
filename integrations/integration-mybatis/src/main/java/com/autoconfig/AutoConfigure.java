package com.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(AutoInitService.class)
@EnableConfigurationProperties(AutoProperties.class)
public class AutoConfigure {

    public String getName() {
        return "AutoAutoConfigure getName=============================";
    }

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    public AutoInitService autoInitServiceInit() {
        System.out.println("autoInitServiceInit=============================");
        return new AutoInitService();
    }
}