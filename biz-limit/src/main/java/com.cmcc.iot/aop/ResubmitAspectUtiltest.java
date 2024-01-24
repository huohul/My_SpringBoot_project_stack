package com.cmcc.iot.aop;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cmcc.iot.annotation.Resubmit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ResubmitAspectUtil
 * @Descripition 防重复提交切面类
 * @Author
 * @Date 2023/8/31 410:52
 */
@Slf4j
@Aspect
@Component
public class ResubmitAspectUtiltest {

    // key: 固定前缀
    private static final String FIXED_KEY = "repeat:data:";
    @Autowired
    private StringRedisTemplate redisTemplate;

    // 切入点(注入自定义注解)
    // 切点分为execution方式和annotation方式。前者可以用路径表达式指定哪些类织入切面，后者可以指定被哪些注解修饰的代码织入切面。
    // @Pointcut("@annotation(com.test.Resubmit)")
    @Pointcut("@annotation(com.cmcc.iot.annotation.Resubmit)")
    public void resubmitPointcut() {

    }

    /**
     * 同一接口API防重复(频繁)提交
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("resubmitPointcut()")
    public Object aroundPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            // 参数名称
            String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            // 参数值
            Object[] values = joinPoint.getArgs();
            Map<String, Object> mapParam = new HashMap<>();
            for (int i = 0; i < parameterNames.length; i++) {
                mapParam.put(parameterNames[i], values[i]);
            }

            // 校验入参非空
            if (Objects.isNull(mapParam)) {
                return new RuntimeException("参数为空!");
            }

            // 解析入参获取请求id
            String param = JSON.toJSONString(mapParam.get("requestData"));
            log.info("[解析入参获取请求id] ResubmitInterceptorUtil.aroundPointcut param : {}", JSON.toJSONString(param));
            if (StringUtils.isBlank(param)) {
                return new RuntimeException("参数为空!");
            }

            JSONObject jsonObject = JSON.parseObject(param);
            if (Objects.isNull(jsonObject) || jsonObject.size() <= 0) {
                return new RuntimeException("参数为空!");
            }
            // 请求id(每次请求的唯一标识)
            String requestId = jsonObject.getJSONObject("header").get("token").toString();
            if (StringUtils.isBlank(requestId)) {
                throw new RuntimeException("[解析入参获取请求id]异常!,请检查入参请求id");
            }

            // 获取自定义注解-防重复注解（@Resubmit）
            Resubmit resubmit = method.getAnnotation(Resubmit.class);
            // 默认过期时间、单位:秒
            int millis = resubmit.value();

            // String key = method.getDeclaringClass().getName() + "#" + method.getName();
            String key = method.getName() + "_requestId_" + requestId;
            // key: 固定前缀
            key = FIXED_KEY + key;
            Long expire = redisTemplate.opsForValue().getOperations().getExpire(key);
            if (expire != null && expire.compareTo(0L) >= 0) {
                throw new RuntimeException(resubmit.messge());
            }

            // 设置key:value 过期时间 单位秒
            redisTemplate.opsForValue().set(key, String.valueOf(System.currentTimeMillis()), millis, TimeUnit.SECONDS);

            return joinPoint.proceed();
        } catch (RuntimeException e) {
            log.error("【请求过于频繁,请稍后再试!!!】", e);
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            log.error("【拦截接口API防重复(频繁)提交异常!!!】", e);
            throw new RuntimeException(e.getMessage());
        }
    }

}