package com.park.ssm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import com.park.ssm.annotation.UnEditableField;

public class PersistentUtil {

	/**
	 * 把sourceObj中的和target的共同属性，从sourceObj中复制到target中
	 * 
	 * @param target
	 *            被更新属性的对象
	 * @param sourceObj
	 *            提供更新的属性的对象
	 * @param baseClass 提供用于限制复制值范围的基准class
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> void merge(Object target, Object sourceObj,Class<T> baseClass) throws IllegalArgumentException, IllegalAccessException {
		Set<Field> fieldSet = getCommendField(target.getClass(), sourceObj.getClass(),baseClass);
		for (Field field : fieldSet) {
			boolean accessiable = field.isAccessible();
			field.setAccessible(true);
			Object sourceValue = field.get(sourceObj);
			if (sourceValue != null) {
				field.set(target, sourceValue);
			}
			field.setAccessible(accessiable);
		}
	}

	private static Set<Field> getAllField(Class<?> clazz) {
		Set<Field> fieldSet = new HashSet<>();
		Class<?> currentClass = clazz;
		if (clazz != null) {
			do {
				Field[] fields = currentClass.getDeclaredFields();
				// 获取能够进行排除的Field的modifier
				int modifier = Modifier.STATIC | Modifier.FINAL;
				for (Field field : fields) {
					if ((field.getModifiers() & modifier) == 0) {
						fieldSet.add(field);
					}
				}
				currentClass = currentClass.getSuperclass();
			} while (!currentClass.equals(Object.class));
		}
		return fieldSet;
	}
	
	/**
	 * 获取两个class直接的共同Field，其中BaseClass是用于限定Field的基准范围，用于剔除一些子类多于的属性
	 * @param targetClass
	 * @param sourceClass
	 * @param baseClass
	 * @return
	 */
	private static Set<Field> getCommendField(Class<?> targetClass, Class<?> sourceClass, Class<?> baseClass) {
		Set<Field> targetSet = getAllField(targetClass), sourceSet = getAllField(sourceClass);
		targetSet.removeIf(new Predicate<Field>() {
			@Override
			public boolean test(Field t) {
				// TODO Auto-generated method stub
				return !sourceSet.contains(t);
			}
		});
		if(baseClass != null) {
			Set<Field> baseSet=getAllField(baseClass);
			targetSet.removeIf(new Predicate<Field>() {
				@Override
				public boolean test(Field t) {
					// TODO Auto-generated method stub
					return !baseSet.contains(t);
				}
			});
		}
		return targetSet;
	}

	public static <T> T newInstanceByMap(Class<T> clazz, Map<String, ?> map) {
		T object = null;
		try {
			object = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				Object value = map.get(fieldName);
				if (value != null && value.getClass().isInstance(field.getType())) {
					boolean accessible = field.isAccessible();
					field.setAccessible(true);
					field.set(object, value);
					field.setAccessible(accessible);
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 查找新对象和数据库中的对象属性中的差别，筛选出有差别的属性
	 * 
	 * @param oldObj
	 *            数据库中的旧对象
	 * @param newObj
	 *            前端传入的有更新过信息的对象
	 * @param baseClass 用于限制Field的查找范围，剔除一些子类多余的属性
	 * @return Map 返回属性不同的Map 没有需要进行更新的属性返回null
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> Map<String, Object> different(Object oldObj, Object newObj, Class<T> baseClass)
			throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> result = new HashMap<>();
		Set<Field> fieldSet = getCommendField(oldObj.getClass(), newObj.getClass(), baseClass);
		// 查找共同Field中存在不同的Field
		for (Field field : fieldSet) {
			// 判断是否能够更改
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			if (field.getAnnotation(UnEditableField.class) == null) {
				// 更新的对象当中属性不为null并且有不同
				if (isDifferent(field, oldObj, newObj)) {
					result.put(field.getName(), field.get(newObj));
				}
			}
			// 还原原始accessible
			field.setAccessible(accessible);
		}
		return result.isEmpty() ? null : result;
	}

	private static boolean isDifferent(Field field, Object target, Object newObj)
			throws IllegalArgumentException, IllegalAccessException {
		return field.get(newObj) != null && !field.get(target).equals(field.get(newObj));
	}
}
