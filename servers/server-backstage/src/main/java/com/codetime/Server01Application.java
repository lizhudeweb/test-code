package com.codetime;

import com.autoconfig.AutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * jar
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Server01Application {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
//        SpringApplication.run(Server01Application.class, args);
//        System.out.println("启动成功=============================");

        ConfigurableApplicationContext context = SpringApplication.run(Server01Application.class, args);
        System.out.println("启动成功=============================");
        AutoConfigure ext = context.getBean(AutoConfigure.class);
        System.out.println(ext.getName());
        System.out.println(ext.systemInit());
    }

}
