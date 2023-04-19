package com.codetime.myweb.interceptor;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codetime.common.enums.ErrorCodeEnum;
import com.codetime.common.pojo.CommonResult;
import com.codetime.common.utils.StringUtils;
import com.codetime.myweb.aop.LimitRepeat;
import com.codetime.myweb.redis.CacheConstants;
import com.codetime.myweb.redis.RedisUtil;
import com.codetime.web.constant.AuthConstants;
import com.codetime.web.tools.ServletUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.alibaba.fastjson2.JSON;
import sun.security.util.SecurityConstants;

@Component
public class LimitRepeatInterceptor implements HandlerInterceptor {

    private static final Cache<String, Object> CACHES =
            CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(5, TimeUnit.SECONDS).build();

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            LimitRepeat annotation = method.getAnnotation(LimitRepeat.class);
            if (annotation == null) {
                return true;
            }
            if (checkRepeat(request, annotation)) {
                CommonResult commonResult = CommonResult.error(ErrorCodeEnum.REPEATED_REQUEST);
                ServletUtil.respString(response, JSON.toJSONString(commonResult));
                return false;
            }
        }
        return true;
    }

    private boolean checkRepeat(HttpServletRequest request, LimitRepeat annotation) {
        // 用户 KeyGenerator keyGenerator = new SimpleKeyGenerator();
        String authKey = request.getHeader(AuthConstants.AUTHORIZATION);
        String repeatKey = CacheConstants.REPEAT_KEY + request.getRequestURI() + authKey;
        String key = redisUtil.get(repeatKey);
        if (StringUtils.isEmpty(key)) {
            redisUtil.set(repeatKey, System.currentTimeMillis(), annotation.interval(), TimeUnit.MILLISECONDS);
            return true;
        }
        return false;
    }
}
