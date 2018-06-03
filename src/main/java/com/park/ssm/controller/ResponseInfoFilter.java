package com.park.ssm.controller;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.park.ssm.annotation.Permission;
import com.park.ssm.entity.InnerUser;


/**
 * 对返回的信息进行过滤，把不满足权限控制的属性过滤为null
 * @author LZH
 *
 */
@SuppressWarnings("rawtypes")
@ControllerAdvice
@Scope("request")
public class ResponseInfoFilter implements ResponseBodyAdvice {
	private static Logger logger = LogManager.getLogger(GlobalController.class);
	
	private InnerUser innerUser;
	
	

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		// TODO Auto-generated method stub
		//initFilter
		if(request instanceof HttpServletRequest) {
			innerUser=(InnerUser) ((HttpServletRequest)request).getSession().getAttribute("innerUser");
		}
		if(request instanceof ServletServerHttpRequest) {
			innerUser=(InnerUser)((ServletServerHttpRequest)request).getServletRequest().getSession().getAttribute("innerUser");
		}
		
		Object value=body;
		if(selectedContentType.equals(MediaType.APPLICATION_JSON_UTF8)) {
			value=filterJSONInfo(body);
		}
		
		return value;
	}

	private Object filterJSONInfo(Object object) {
		if (object instanceof Map) {
			return handleMap((Map) object);
		}
		if (object instanceof Collection) {
			return handleCollection((Collection) object);
		}
		return handleBean(object);
	}

	private Map<String, Object> handleMap(Map map) {
		Map<String, Object> resultMap = new HashMap<>();
		for (Object key : map.keySet()) {
			Object value = map.get(key);
			Object resultValue=null;
			if (value instanceof Collection) {
				resultValue=handleCollection((Collection) value);
			}else if (value instanceof Map) {
				resultValue=handleMap((Map) value);
			}else {
				resultValue=handleBean(value);
			}
			if(resultValue!=null) {
				resultMap.put(key.toString(), resultValue);
			}
		}
		return resultMap;
	}

	private Collection<Object> handleCollection(Collection<?> collection) {
		Collection<Object> result = new LinkedList<>();
		for (Object value : collection) {
			Object resultValue=null;
			if (value instanceof Map) {
				resultValue=handleMap((Map<?, ?>) value);
			}else if (value instanceof Collection) {
				resultValue=handleCollection((Collection<?>) value);
			}else {
				resultValue=handleBean(value);
			}
			result.add(resultValue);
		}
		return result;
	}

	private Object handleBean(Object bean) {
		Class<?> beanClass = bean.getClass();
		//InfoControllerClassSet非空，通过InfoControllerClassSet判断
		if(! InfoControllerClassSet.isEmpty()) {
			if(! InfoControllerClassSet.contains(beanClass)) {
				return bean;
			}
		//InfoControllerClassSet空，则通过package判断
		}else if(!beanClass.getPackage().equals(Package.getPackage("com.park.ssm.entity"))) {
			return bean;
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		Class<?> tmpClass = beanClass;
		//遍历所有除了Object类 的所有Field
		while (!tmpClass.equals(Object.class)) {
			Field[] fields = tmpClass.getDeclaredFields();
			Permission classDefaultPermission = tmpClass.getAnnotation(Permission.class);
			//遍历改Class下的所有Field
			for (Field field : fields) {
				Object value=doFilterField(field, classDefaultPermission, bean);
				if(value!=null) {
					resultMap.put(field.getName(), value);
				}
				
			}
			tmpClass=tmpClass.getSuperclass();
		}

		return resultMap;
	}
	private Object doFilterField(Field field,Permission classDefaultPermission,Object bean) {
		//排除固定标识符的Field
		int modifier=Modifier.STATIC;
		if((field.getModifiers() & modifier) != 0) {
			return null;
		}
		
		Object value = null;
		// 把处理Field处理成能够访问
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			// 非集合类判断是否需要进行过滤
			Permission fieldPermission = field.getAnnotation(Permission.class);
			fieldPermission = fieldPermission != null ? fieldPermission : classDefaultPermission;
			if (checkPermission(fieldPermission)) {
				// 判断是否集合类
				Class<?> fieldType = field.getType();
				if (fieldType.isAssignableFrom(Collection.class)) {
					value = handleCollection((Collection) field.get(bean));
				}else if (fieldType.isAssignableFrom(Map.class)) {
					value = handleMap((Map) field.get(bean));
				}else {
					value = field.get(bean);
				}
			}
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		} finally {
			field.setAccessible(accessible);
		}
		return value;
	}
	
	private boolean checkPermission(Permission permission) {
		// 判断注解的方法是否需要权限控制
		if (permission == null || permission.haveControl() == false) {
			return true;
		}
		// 判断是否满足权限控制
		if(innerUser!=null) {
			int typeflag = innerUser.getTypeflag();
			for (Permission.Type permissionRole : permission.value()) {
				if (permissionRole.getInd() == typeflag) {
					return true;
				}
			}
		}
		//没有进行登录，或者不满足权限要求
		return false;
	}
	
	private static Set<Class<?>> InfoControllerClassSet;
	/**
	 * 初始化InfoControllerClassSet，扫描包下的所有类，查看是否有权限控制的注解。InfoControllerClassSet用于记录扫描过程中有Permission注解的类
	 */
	private static void initClass() {
		InfoControllerClassSet=new HashSet<>();
		try {
			File classPath=new ClassPathResource("/").getFile();
			Set<File> files=new LinkedHashSet<>();
			classPath.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					if(pathname.isDirectory()) {
						pathname.listFiles(this);
					}
					if(pathname.isFile() && pathname.getName().endsWith(".class")) {
						files.add(pathname);
					}
					return false;
				}
			});
			String basePath=classPath.getPath();
			for(File file:files) {
				String className=file.getPath();
				className=className.replace(basePath+File.separatorChar, "").replace(".class", "").replace(File.separatorChar,'.');
				Class<?> clazz=null;
				try {
					clazz = Class.forName(className);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(clazz==null) {
					continue;
				}
				if(clazz.getAnnotation(Permission.class)!=null) {
					InfoControllerClassSet.add(clazz);
				}else {
					Field[] fields=clazz.getDeclaredFields();
					boolean flag=false;
					for(Field field:fields) {
						boolean accessible=field.isAccessible();
						field.setAccessible(true);
						if(field.getAnnotation(Permission.class) != null) {
							InfoControllerClassSet.add(clazz);
							flag=true;
						}
						field.setAccessible(accessible);
						if(flag==true) {
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static {
		initClass();
	}
}
