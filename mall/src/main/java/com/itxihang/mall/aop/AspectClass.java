package com.itxihang.mall.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AspectClass {
    @Pointcut("execution(* com.itxihang.mall.service.impl.*.*(..))")
    public void pointCut(){}
    @Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("前置通知方法名："+methodName+",参数："+args);
    }
    @After("pointCut()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("后置通知方法名："+methodName+",参数："+args);
    }
    @AfterReturning(value = "pointCut()",returning ="result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("返回通知方法名："+methodName+",结果："+args);
    }
    @AfterThrowing(value = "pointCut()",throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint,Throwable ex){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("异常通知方法名："+methodName+",异常："+ ex);
    }
//    @Around("pointCut()")
//    public Object aroundMethod(ProceedingJoinPoint joinPoint){
//        String methodName = joinPoint.getSignature().getName();
//        String args = Arrays.toString(joinPoint.getArgs());
//        Object result = null;
//        try {
//            System.out.println("环绕通知--方法执行之前");
//             result = joinPoint.proceed();
//            System.out.println("环绕通知--方法执行之后");
//        } catch (Throwable throwable) {
//            System.out.println("环绕通知--方法出现异常");
//        }finally {
//            System.out.println("环绕通知--方法执行完毕");
//        }
//        return  result;
//    }
}
