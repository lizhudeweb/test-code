package com.codetime;

import com.autoconfig.AutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Server01Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
//        SpringApplication.run(Server01Application.class, args);
//        System.out.println("启动成功=============================");

        ConfigurableApplicationContext context = SpringApplication.run(Server01Application.class, args);
        System.out.println("启动成功=============================");
        AutoConfigure ext = context.getBean(AutoConfigure.class);
        System.out.println(ext.getName());
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Server01Application.class);
    }

}
