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
	
	private static boolean debugFlag;
	
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
	
	//用于查看当前环境是否是debug环境，即查看JVM参数中是否有输入debug.flag=true
	static {
		String debugFlagString=System.getProperty("debug.flag");
		if(debugFlagString != null && Boolean.valueOf(debugFlagString)) {
			debugFlag=true;
		}else {
			debugFlag=false;
		}
	}
}
