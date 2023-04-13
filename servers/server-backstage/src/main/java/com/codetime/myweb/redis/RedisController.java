package com.codetime.myweb.redis;

import com.codetime.myweb.controller.MyController;
import com.codetime.system.domain.SystemUserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/redis")
public class RedisController {
    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping(value = "/getKey")
    public String getKey(HttpServletRequest request, String key) {
        logger.info((String) request.getSession().getAttribute("key"));

        return redisUtil.get(key);
    }

    @GetMapping(value = "/setKey")
    public void setKey(HttpServletRequest request, String key, String value) {
        request.getSession().setAttribute(key, value);
        redisUtil.set(key, value);
    }

    @GetMapping(value = "/setJavaBean")
    public void setBean(Long id, String account, String password) {
        SystemUserAccount user = SystemUserAccount.builder().id(id).account(account).password(password).build();
        redisUtil.set(String.valueOf(id), user);
    }

    @GetMapping(value = "/getJavaBeanById")
    public Object getBean(String key) {
        return redisUtil.get(key);
    }

    @GetMapping(value = "/flushDB")
    public void flushDB() {
        redisUtil.flushDB();
    }

}
