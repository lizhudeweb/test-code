package com.codetime.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/myweb/rest")
public class IndexController2 {
    private static final Logger logger = LoggerFactory.getLogger(IndexController2.class);

    @GetMapping(value = "/1")
    @ResponseBody
    public String getKey(HttpServletRequest request, String key) {
        List list = null;
        list.add("1");
        logger.info("RedisController getKey:{}", key);
        return "1";
    }

    @GetMapping(value = "/setKey")
    public void setKey(HttpServletRequest request, String key, String value) {
        logger.info("RedisController setKey:{}, value:{}", key, value);
    }

}
