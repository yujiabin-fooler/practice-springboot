package com.jiabin.menu.role.practice.config.aspect;

import com.jiabin.menu.role.practice.config.exception.CommonException;
import com.jiabin.menu.role.practice.dao.MenuMapper;
import com.jiabin.menu.role.practice.entity.dto.BaseDTO;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class CheckPermissionsAspect {

    @Autowired
    private MenuMapper menuMapper;

    @Pointcut("@annotation(com.jiabin.menu.role.practice.config.aspect.CheckPermissions)")
    public void checkPermissions() {}

    @Before("checkPermissions()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        Long loginUserId = null;
        Object[] args = joinPoint.getArgs();
        Object parobj = args[0];
        //用户请求参数实体类中的用户ID
        if(!Objects.isNull(parobj) && parobj instanceof BaseDTO){
            loginUserId = ((BaseDTO) parobj).getLoginUserId();
        }
        if(!Objects.isNull(loginUserId)){
            // 获取方法上注解值
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            CheckPermissions annotation = method.getAnnotation(CheckPermissions.class);
            if(Objects.nonNull(annotation) && StringUtils.isNotBlank(annotation.value())){
                //通过用户ID、菜单编码查询是否有关联
                int count = menuMapper.selectAuthByUserIdAndMenuCode(loginUserId, annotation.value());
                if(count == 0){
                    throw new CommonException("接口无访问权限");
                }
            }
        }
    }
}
