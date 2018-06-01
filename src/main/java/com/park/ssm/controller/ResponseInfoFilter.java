package com.park.ssm.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.park.ssm.annotation.Permission;
import com.park.ssm.entity.InnerUser;

@SuppressWarnings("rawtypes")
@ControllerAdvice
@Scope("request")
public class ResponseInfoFilter/* implements ResponseBodyAdvice */{
	/*private static Logger logger=LogManager.getLogger(GlobalController.class);

	@Autowired
	private HttpServletRequest request;
	
	private InnerUser innerUser;
	
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		// TODO Auto-generated method stub
		innerUser=(InnerUser) request.getAttribute("innerUser");
		System.out.println("support");
		System.out.println(returnType);
		System.out.println(returnType.getClass());
		System.out.println(converterType);
		System.out.println(converterType.getClass());
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		// TODO Auto-generated method stub
		System.out.println("beforeBodyWrite");
		System.out.println(body);
		System.out.println(body.getClass());
		System.out.println("returnType:"+returnType+"; class :"+returnType.getClass());
		System.out.println("selectedContentType:"+selectedContentType+"; class :"+selectedContentType.getClass());
		System.out.println("selectedConverterType:"+selectedConverterType+"; class :"+selectedConverterType.getClass());
		System.out.println(selectedContentType.equals(MediaType.APPLICATION_JSON_UTF8));
		if(selectedContentType.equals(MediaType.APPLICATION_JSON_UTF8)) {
			Object returnValue=filterJSONInfo(body);
			System.out.println(returnValue);
			return returnValue;
		}
		
		return body;
	}
	

	
	
	private Object filterJSONInfo(Object object) {
		Map<String, Object> resultMap=new HashMap<>();
		if(object instanceof Map) {
			return handleMap((Map) object);
		}
		if(object instanceof Collection) {
			return handleCollection((Collection)object);
		}
		return handleBean(object);
	}
	private Map<String,Object> handleMap(Map map) {
		Map<String, Object> resultMap=new HashMap<>();
		for(Object key:map.keySet()) {
			Object value=map.get(key);
			if(value instanceof Collection) {
				resultMap.put(key.toString(),handleCollection((Collection)value));
			}
			if(value instanceof Map) {
				resultMap.put(key.toString(),handleMap((Map)value));
			}
			resultMap.put(key.toString(), handleBean(value));
		}
		return resultMap;
	}
	private Collection<Object> handleCollection(Collection<?> collection) {
		Collection<Object> result=new LinkedList<>();
		for(Object value:collection) {
			if(value instanceof Map) {
				result.add(handleMap((Map<?,?>)value));
			}
			if(value instanceof Collection) {
				result.add(handleCollection((Collection<?>)value));
			}
			result.add(handleBean(value));
		}
		return result;
	}
	private Map<String,Object> handleBean(Object bean) {
		Class<?> beanClass=bean.getClass();
		Field[] fields=beanClass.getDeclaredFields();
		Permission classDefaultPermission=beanClass.getAnnotation(Permission.class);
		Map<String, Object> resultMap=new HashMap<>(); 
		for(Field field:fields) {
			//排除static的属性
			int modifier=field.getModifiers();
			if((modifier & Modifier.STATIC)!=0) {
				continue;
			}
			Object value=null;
			try {
				//把处理Field处理成能够访问
				boolean accessible=field.isAccessible();
				field.setAccessible(true);
				//非集合类判断是否需要进行过滤
				Permission fieldPermission=field.getAnnotation(Permission.class);
				fieldPermission=fieldPermission==null?fieldPermission:classDefaultPermission;
				if(checkPermission(fieldPermission)) {
					//判断是否集合类
					Class<?> fieldType=field.getType();
					if(fieldType.isAssignableFrom(Collection.class)) {
						value=handleCollection((Collection)field.get(bean));
					}
					if(fieldType.isAssignableFrom(Map.class)) {
						value=handleMap((Map)field.get(bean));
					}
					value=field.get(bean);
				}
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
			resultMap.put(field.getName(), value);
		}
		return resultMap;
	}
	
	private boolean checkPermission(Permission permission) {
		//判断注解的方法是否需要权限控制
		if(permission==null || permission.haveControl()==false) {
			return true;
		}
		//判断是否满足权限控制
		int typeflag=innerUser.getTypeflag();
		for(Permission.Type permissionRole:permission.value()) {
			if(permissionRole.getInd()==typeflag){
				return true;
			}
		}
		return false;
	}*/
}
