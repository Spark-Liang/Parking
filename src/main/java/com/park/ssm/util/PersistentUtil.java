package com.park.ssm.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import com.park.ssm.annotation.UnEditableField;

public class PersistentUtil {

	/**合并两个对象的所有属性
	 * 
	 * @param target 被更新属性的对象
	 * @param origin 提供更新的属性
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void merge(Object target,Object origin) throws IllegalArgumentException, IllegalAccessException{
		Set<Field> fieldSet=getCommendField(target.getClass(), origin.getClass());
		for(Field field:fieldSet) {
			boolean accessiable=field.isAccessible();
			field.setAccessible(true);
			Object originValue=field.get(origin);
			if(originValue!=null) {
				field.set(target,originValue);
			}
			field.setAccessible(accessiable);
		}
	}
	private static Set<Field> getAllField(Class<?> clazz){
		Set<Field> fieldSet=new HashSet<>();
		Class<?> currentClass=clazz;
		do {
			Field[] fields=currentClass.getDeclaredFields();
			fieldSet.addAll(Arrays.asList(fields));
			currentClass=currentClass.getSuperclass();
		}while(!currentClass.equals(Object.class));
		return fieldSet;
	}
	private static Set<Field> getCommendField(Class<?> targetClass,Class<?> originClass){
		Set<Field> 
			targetSet=getAllField(targetClass)
			,originSet=getAllField(originClass);
		targetSet.removeIf(new Predicate<Field>() {
			@Override
			public boolean test(Field t) {
				// TODO Auto-generated method stub
				return !originSet.contains(t);
			}
		});
		return targetSet;
	}
	
	/**查找两个对象之间共同的并且可以进行更新的属性Field中，存在的不同的Field
	 * 
	 * @param target 需要存储到数据库的对象
	 * @param origin 前端传入的有更新过信息的对象
	 * @return Map 返回属性不同的Map 没有需要进行更新的属性返回null
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Map<String, Object> different(Object target,Object origin) throws IllegalArgumentException, IllegalAccessException{
		Map<String, Object> result=new HashMap<>();
		Set<Field> fieldSet=getCommendField(target.getClass(), origin.getClass());
		//查找共同Field中存在不同的Field
		for(Field field:fieldSet) {
			//判断是否能够更改
			boolean accessible=field.isAccessible();
			field.setAccessible(true);
			if(field.getAnnotation(UnEditableField.class)==null) {
				//更新的对象当中属性不为null并且有不同
				if(isDifferent(field,target,origin)) {
					result.put(field.getName(), field.get(origin));
				}
			}
			//还原原始accessible
			field.setAccessible(accessible);
		}
		return result.isEmpty()?null:result;
	}
	
	private static boolean isDifferent(Field field,Object target,Object origin) throws IllegalArgumentException, IllegalAccessException {
		return field.get(origin) != null && !field.get(target).equals(field.get(origin));
	}
}
