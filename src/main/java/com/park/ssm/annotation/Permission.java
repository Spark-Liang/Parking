package com.park.ssm.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * 权限控制注解
 * 
 * <ol>
 * <li>注解在方法上是限制该方法的访问角色
 * <li>注解在类上是整个类默认允许的访问角色
 * <li>方法级别注解高于类级别注解
 * <ol>
 * @param haveControl 指是否需要进行权限控制
 * @param value Type[] 指能够允许的权限角色
 * 
 * @author ASNPHXJ<br>
 * {@value}用于控制该方法或者整个类的允许访问的角色，比如允许ADMIN就添加 “Permission.ADMIN”
 */
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Permission {

	Type[] value();
	boolean haveControl() default true;
	
	public static enum Type{
		ADMIN(0,"ADMIN"),MANAGER(1,"MANAGER"),OPERATOR(2,"OPERATOR");
		
		private int ind;
		private String name;
		private Type(int ind, String name) {
			this.ind = ind;
			this.name = name;
		}
		public int getInd() {
			return ind;
		}
		public String getName() {
			return name;
		}
	}
	
	
}
