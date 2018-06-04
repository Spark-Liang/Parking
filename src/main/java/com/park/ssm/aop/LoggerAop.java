package com.park.ssm.aop;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

import com.park.ssm.util.CommonsDebugFlag;

@Aspect
@Component
public class LoggerAop {
	private static ThreadLocal<Throwable> currentLastThrowable=new ThreadLocal<>();
	private Logger logger=LogManager.getLogger(this.getClass());
	
	private static boolean debugFlag=CommonsDebugFlag.isDebug();
	
	@Pointcut(value="execution(* com.park..*.*(..))")
	public void exceptionPointCut() {}
	
	@AfterThrowing(pointcut="exceptionPointCut()",throwing="e")
	public void logExecption(Throwable e) {
		if(currentLastThrowable.get() != null) {
			Throwable lastThrowable=currentLastThrowable.get();
			if(lastThrowable.equals(e)) {
				return;
			}
		}
		currentLastThrowable.set(e);
		logger.info(e);
		if(debugFlag) {
			System.err.println("Exception stack trace is :\n");
			e.printStackTrace();
			
		}
	}
	
}
