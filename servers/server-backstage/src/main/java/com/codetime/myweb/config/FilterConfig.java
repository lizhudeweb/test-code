package com.codetime.myweb.config;

import com.codetime.myweb.filter.MyFilter1;
import com.codetime.myweb.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    /**
     * ResponseBodyAdvice
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean<MyFilter1> filterContainer = new FilterRegistrationBean<>();
        filterContainer.setFilter(new MyFilter1());
        //filter pattern
        filterContainer.addUrlPatterns("/myweb/*");
        //filter name
        filterContainer.setName("MyFilter");
        //filter order
        filterContainer.setOrder(1);
        return filterContainer;
    }

    @Bean
    public FilterRegistrationBean myFilter2() {
        FilterRegistrationBean<MyFilter2> filterContainer = new FilterRegistrationBean<>();
        filterContainer.setFilter(new MyFilter2());
        //filter pattern
        filterContainer.addUrlPatterns("/myweb/*");
        //filter name
        filterContainer.setName("MyFilter2");
        //filter order
        filterContainer.setOrder(2);
        return filterContainer;
    }

}
