package com.park.ssm.aop;

import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAop {
	private static ThreadLocal<Throwable> currentLastThrowable=new ThreadLocal<>();
	private Logger logger=LogManager.getLogger(this.getClass());
	
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
		StringWriter writer=new StringWriter();
		writer.write("Exception stack trace is :\n");
		e.printStackTrace(new PrintWriter(writer));
		logger.info(writer.toString());
	}
}
