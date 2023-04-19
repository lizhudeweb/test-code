package com.codetime.myweb.config;

import com.codetime.common.utils.StringUtils;
import com.codetime.myweb.filter.MyFilter1;
import com.codetime.myweb.filter.MyFilter2;
import com.codetime.myweb.filter.RepeatableFilter;
import com.codetime.myweb.filter.XssFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FilterConfig {

    @Value("${xss.excludes}")
    private String excludes;

    @Value("${xss.urlPatterns}")
    private String urlPatterns;

    /**
     * ResponseBodyAdvice
     *
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


    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    @ConditionalOnProperty(value = "xss.enabled", havingValue = "true")
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        // 过滤urls
        registration.addUrlPatterns(StringUtils.split(urlPatterns, ","));
        registration.setName("xssFilter");
        registration.setOrder(Integer.MAX_VALUE - 1);
        Map initParameters = new HashMap<String, String>();
        // 排除urls
        initParameters.put("excludes", excludes);
        registration.setInitParameters(initParameters);
        return registration;
    }


    @Bean
    public FilterRegistrationBean repeatableFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RepeatableFilter());
        registration.addUrlPatterns("/*");
        registration.setName("repeatableFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

}
