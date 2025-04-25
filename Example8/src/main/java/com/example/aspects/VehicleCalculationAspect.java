package com.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class VehicleCalculationAspect {

    private Logger logger = Logger.getLogger(VehicleCalculationAspect.class.getName());


    @AfterThrowing(value = "execution(* com.example.services.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {

        logger.info(joinPoint.getSignature() + "  Big Error:" + "\n" + ex.getMessage());
    }

    @AfterReturning(value = "execution(* com.example.services.*.*(..))", returning = "retval")
    public void logReturnData(JoinPoint joinPoint, Object retval ) {

        logger.info(joinPoint.getSignature() + " Method succesfully processed with the status: " + "\n" + retval.toString());
    }
}
