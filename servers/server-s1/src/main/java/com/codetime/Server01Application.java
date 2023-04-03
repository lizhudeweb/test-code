package com.codetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * jar
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Server01Application {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Server01Application.class, args);
        System.out.println("启动成功=============================");
    }

}
