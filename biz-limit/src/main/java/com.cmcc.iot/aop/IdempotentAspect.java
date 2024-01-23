package com.cmcc.iot.aop;

import com.cmcc.iot.annotation.Idempotent;
import com.cmcc.iot.entity.RequestData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.cmcc.iot.service.RedisIdempotentStorage;
import com.cmcc.iot.util.AopUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class IdempotentAspect {

    @Resource
    private RedisIdempotentStorage redisIdempotentStorage;

    @Pointcut("@annotation(com.cmcc.iot.annotation.Idempotent)")
    public void idempotent() {
    }

    @Around("idempotent()")
    public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Idempotent idempotent = method.getAnnotation(Idempotent.class);

        String field = idempotent.field();
        String name = idempotent.name();
        Class clazzType = idempotent.type();

        String token = "";

        Object object = clazzType.newInstance();
        Map<String, Object> paramValue = AopUtils.getParamValue(joinPoint);
        if (object instanceof RequestData) {
            RequestData idempotentEntity = (RequestData) paramValue.get(name);
            token = String.valueOf(AopUtils.getFieldValue(idempotentEntity.getHeader(), field));
        }

        if (redisIdempotentStorage.delete(token)) {
            return joinPoint.proceed();
        }
        return "重复请求";
    }
}