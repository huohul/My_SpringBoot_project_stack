package com.cmcc.iot.aop;

import com.alibaba.fastjson2.JSONObject;
import com.cmcc.iot.annotation.TokenBucketLimiter;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @description 基于guava的aop实现
 */
@Aspect
@Component
@Slf4j
public class GuavaLimiterAop {

    private final Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();

    /**
     * 令牌桶
     */
    @Pointcut("@annotation(com.cmcc.iot.annotation.TokenBucketLimiter)")
    public void aspect() {
    }

    @Around(value = "aspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.debug("准备限流");
        Object target = point.getTarget();
        String targetName = target.getClass().getName();
        String methodName = point.getSignature().getName();
        Object[] arguments = point.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Class<?>[] argTypes = ReflectUtils.getClasses(arguments);
        Method method = targetClass.getDeclaredMethod(methodName, argTypes);
        // 获取目标method上的限流注解@Limiter
        TokenBucketLimiter limiter = method.getAnnotation(TokenBucketLimiter.class);
        RateLimiter rateLimiter;
        Object result = null;
        if (null != limiter) {
            // 以 class + method + parameters为key，避免重载、重写带来的混乱
            String key = targetName + "." + methodName + Arrays.toString(argTypes);
            rateLimiter = rateLimiters.get(key);
            if (null == rateLimiter) {
                // 获取限定的流量
                // 为了防止并发
                rateLimiters.putIfAbsent(key, RateLimiter.create(limiter.value()));
                rateLimiter = rateLimiters.get(key);
            }
            boolean b = rateLimiter.tryAcquire();
            if (b) {
                log.debug("得到令牌，准备执行业务");
                result = point.proceed();
            } else {
                HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("success", false);
                jsonObject.put("msg", "限流中");
                try {
                    output(resp, jsonObject.toJSONString());
                } catch (Exception e) {
                    log.error("error,e:{}", e);
                }
            }
        } else {
            result = point.proceed();
        }
        log.debug("退出限流");
        return result;
    }

    public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
}