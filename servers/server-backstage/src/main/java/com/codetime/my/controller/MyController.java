package com.codetime.my.controller;

import com.codetime.common.pojo.CommonResult;
import com.codetime.my.domain.MyAccount;
import com.codetime.system.service.ISystemUserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codetime.common.pojo.CommonResult.success;

/**
 * 用户信息Controller
 *
 * @author test-code
 * @since 2023-4-8
 */
@RestController
@RequestMapping("/my/")
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    /**
     * 获取用户信息详细信息
     */
    @GetMapping(value = "/{id}")
    public CommonResult getInfo(@PathVariable("id") Long id) {

        logger.info("======================id:{}", id);
        return success();
    }

    @GetMapping(value = "/test")
    public CommonResult buyPhone(@Validated MyAccount account, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, Object> errorMap = new HashMap<>(10);
            fieldErrors.forEach(error -> {
                        logger.error("字段:{}校验失败, Message:{} \n", error.getField(), error.getDefaultMessage());
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
            );
        }
        return success();
    }

}
