package com.example.React_Spring.BikeAspectFolder;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BikeAspect {
    private  static  final Logger logger= LoggerFactory.getLogger(BikeAspect.class);
//return type, classname,method name,args
//Syntax-->execution(return-type package.class.method(parameters))
@Before("execution(* com.example.React_Spring.Service.ServiceClass.getSingleBikeDetails(..))")
    public void LoggingFirst(JoinPoint jp){
        logger.info("LoggingFirst() called before the execution of:"+jp.getSignature().getName());
    }

    @After("execution(* com.example.React_Spring.Service.ServiceClass.getSingleBikeDetails(..))")
    public void LoggingAfter(JoinPoint jp){
        logger.info("LoggingFirst() called after the execution of:"+jp.getSignature().getName());
    }
    @AfterThrowing("execution(* com.example.React_Spring.Service.ServiceClass.getSingleBikeDetails(..))")
    public void LoggingError(JoinPoint jp){
        logger.info("Error Ocurred:");
    }

    @AfterReturning("execution(* com.example.React_Spring.Service.ServiceClass.getSingleBikeDetails(..))")
    public void LoggingResolving(JoinPoint jp){
        logger.info("Error Resolved:");
    }

    @Around("execution(* com.example.React_Spring.Service.ServiceClass.getSingleBikeDetails(..))")
    public Object LoggingAround(ProceedingJoinPoint jp) throws Throwable {
        long start=System.currentTimeMillis();
        Object obj=jp.proceed();
        long end=System.currentTimeMillis();
        logger.info("Total time for the executing the method:"+(end-start));
        return obj;
    }
}


