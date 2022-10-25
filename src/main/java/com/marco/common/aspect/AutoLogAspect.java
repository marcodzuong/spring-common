package com.marco.common.aspect;

import com.marco.common.aspect.annotation.AutoLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author MarcoDuong
 */
@Aspect
@Component
public class AutoLogAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Pointcut("@annotation(com.marco.common.aspect.annotation.AutoLog)")
    public void logPointCut() {

    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param point join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //execution method
        Object result = point.proceed();
        //Execution time (ms)
        long time = System.currentTimeMillis() - beginTime;

        //save log
        saveSysLog(point, time, result);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time, Object obj) {
        System.out.println("" + time + obj + joinPoint);
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            AutoLog logAnnotation = method.getAnnotation(AutoLog.class);
            String value = logAnnotation.value();
            if (value!=null){
                log.error("Exception : " + value);
            }else {
                log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
