package com.codetime.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 扫描的Mapper路径
@MapperScan("com.codetime.**.mapper")
public class MybatisConfig {

}
