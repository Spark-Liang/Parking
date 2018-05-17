package com.park.ssm.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 标记不能进行修改的属性
 * @author ASNPHXJ
 *
 */
@Retention(RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface UnEditableField {
	
}
