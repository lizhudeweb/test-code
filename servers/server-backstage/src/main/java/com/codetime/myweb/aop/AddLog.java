package com.codetime.myweb.aop;

import com.codetime.common.enums.OperateType;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface AddLog {

    /**
     * name
     */
    public String title() default "";

    /**
     * 操作类型
     */
    public OperateType OperateType() default OperateType.DEFAULT;

    /**
     * 保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 保存响应的参数
     */
    public boolean isSaveResponseData() default true;

}
