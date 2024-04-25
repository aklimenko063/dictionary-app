package org.javaacademy.dictionaryapp.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.javaacademy.dictionaryapp.annotation.SleepAnnotation;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SleepAspect {

    @SneakyThrows
    @Around("@annotation(sleepAnnotation)")
    public Object applySleep(ProceedingJoinPoint joinPoint, SleepAnnotation sleepAnnotation) throws Throwable {
        long latency = sleepAnnotation.latency();
        Thread.sleep(latency);
        return joinPoint.proceed();
    }
}
