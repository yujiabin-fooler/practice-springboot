package com.jiabin.json.desensitize.practice.config.aop;

import cn.hutool.db.Page;
import com.jiabin.json.desensitize.practice.config.annotation.ReadableSensitiveVerify;
import com.jiabin.json.desensitize.practice.domain.User;
import com.jiabin.json.desensitize.practice.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shiva   2022-09-17 17:03
 */
@Slf4j
@Aspect
@Component
public class DesensitizedAspect {

    @Around("execution(* com.jiabin.json.desensitize.practice.controller.UserController.listUser(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("环绕通知执行，脱敏开始了");
        // 1.执行方法，先拿到返回值
        Object obj = proceedingJoinPoint.proceed();
        // 2.没有返回值，或者是string类型，那就不搞了
        // 注意：这里也可能已经是json了，所以需要写代码的时候小心点
        if (obj == null || isPrimitive(obj.getClass())) {
            return obj;
        }
        // 3.开始处理数据
        //再次注意，一般我们的返回数据都是 code、msg、data三个字段的。所以注意返回值结构
        dealData(obj);
        return obj;
    }

    /**
     * 基本数据类型和String类型判断
     */
    private boolean isPrimitive(Class<?> clz) {
        try {
            if (String.class.isAssignableFrom(clz) || clz.isPrimitive()) {
                return true;
            } else {
                return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 实际数据处理
     */
    private void dealData(Object obj) {
        if (null == obj) {
            return;
        }
        //一般返回的都是通过泛型，或者obj，所以实际是什么方法要自己手写判断
        Field[] fields = User.class.getDeclaredFields();
        replace(fields, obj);
    }

    /**
     * 脱敏敏感字段
     */
    private void replace(Field[] fields, Object o) {
        try {
            for (Field f : fields) {
                if (f == null) {
                    continue;
                }
                //设置private字段可访问
                f.setAccessible(true);

                // 分析过程：
                // 1.一般返回也就三种，嵌套对象、简单对象、
                // 2.TODO 如果是对象类型的，循环字段检查下有没有嵌套对象，这部分例子里就不写了
                // 3.集合对象，也要特殊处理，递归方法继续调用
                Class<?> curFieldType = f.getType();
                if (curFieldType.equals(List.class)) {
                    List<?> record = (List<?>) f.get(o);
                    if (record != null && !record.isEmpty()) {
                        for (Object obj : record) {
                            Field[] ff = obj.getClass().getDeclaredFields();
                            replace(ff, obj);
                        }
                    }
                }
                //处理普通字符串字段
                ReadableSensitiveVerify annotation = f.getAnnotation(ReadableSensitiveVerify.class);
                if (annotation != null) {
                    f.getType();
                    String valueStr = (String) f.get(o);
                    if (valueStr != null && !"".equals(valueStr)) {
                        String type = annotation.type();
                        if ("name".equals(type)) {
                            f.set(o, CommonUtil.nameDesensitized(valueStr));
                        }
                        if ("mobile".equals(type)) {
                            f.set(o, CommonUtil.mobileDesensitized(valueStr));
                        }
                        if ("idCard".equals(type)) {
                            f.set(o, CommonUtil.idCardDesensitized(valueStr));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
