package com.codetime.myweb.config;

//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import com.codetime.myweb.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class CustomWebConfigure implements WebMvcConfigurer {

    @Resource
    private MyInterceptor interceptor;

//    /**
//     * FastJsonHttpMessageConverter json-java-json
//     * 去掉null
//     * @return
//     */
//    @Bean
//    public HttpMessageConverters customConverters() {
//        return new HttpMessageConverters(new FastJsonHttpMessageConverter());
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/myweb/**").excludePathPatterns("/static/**");
    }
}
