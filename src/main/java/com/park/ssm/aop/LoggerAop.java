package com.park.ssm.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAop {
	private Logger logger=Logger.getLogger("console");
	
	@Pointcut(value="execution(* com.park..*.*(..))")
	public void exceptionPointCut() {}
	
	@AfterThrowing(pointcut="exceptionPointCut()",throwing="e")
	public void logExecption(Throwable e) {
		System.out.println("debug");

		logger.info("AOP logger print");
		logger.info(e.getMessage());
	}
}
