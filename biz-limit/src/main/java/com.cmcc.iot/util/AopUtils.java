package com.cmcc.iot.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class AopUtils {

    public static Object getFieldValue(Object obj, String name) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        Object object = null;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().toUpperCase().equals(name.toUpperCase())) {
                object = field.get(obj);
                break;
            }
        }
        return object;
    }


    public static Map<String, Object> getParamValue(ProceedingJoinPoint joinPoint) {
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Map<String, Object> param = new HashMap<>(paramNames.length);

        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        return param;
    }
}