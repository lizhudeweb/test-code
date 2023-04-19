package com.codetime.myweb.aop;

import com.codetime.web.tools.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;



@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

    /**
     * 处理请求前执行
     */
    @Before(value = "pointCutName() && @annotation(addLog)")
    public void operation(JoinPoint joinPoint, AddLog addLog) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
        logger.info("Before 开始前置通知");
        // 被代理的对象
        Object target = joinPoint.getTarget();
        // 方法的签名
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 被代理的对象的方法反射
        final Method methodTarget = methodSignature.getMethod();
        // 方法上的注解
        AddLog addLog2 = methodTarget.getAnnotation(AddLog.class);

        logger.info("Before 代理对象的包名:{}", target.getClass().getName());
        logger.info("Before 代理对象的方法名:{}", methodTarget.getName());
        logger.info("Before 代理对象的方法参数:{}", Arrays.toString(joinPoint.getArgs()));
        logger.info("Before 请求者IP:{}", IpUtil.getIpAddr());
    }

    @Pointcut(value = "@annotation(com.codetime.myweb.aop.AddLog)")
    public void pointCutName() {
        // do nothing
        logger.info("LogAspect Pointcut =========>");
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "pointCutName() && @annotation(addLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, AddLog addLog, Object jsonResult) {
        handleLog(joinPoint, addLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e
     */
    @AfterThrowing(value = "pointCutName() && @annotation(addLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, AddLog addLog, Exception e) {
        handleLog(joinPoint, addLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, AddLog addLog, final Exception e, Object jsonResult) {
        try {
            if (e != null) {
                // 错误日志
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();

            // 插入日志

            Long costTi = System.currentTimeMillis() - TIME_THREADLOCAL.get();
            logger.info("costTi:{}", costTi);

        } catch (Exception ex) {
            logger.error("异常信息:{}", ex);
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }


}
