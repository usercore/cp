/*
 * Copyright 2016-2099 the original author or authors.
 */
package com.qbb.util.calculator.finance.math.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Class工具类
 * 
 */
public class ClassUtil {

	/**
	 * 实例化对象 - 适用于无参构造方法
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T newInstance(Class<?> clazz) {
		return newInstance(clazz, null, null);
	}

	/**
	 * 实例化对象 - 适用于有参构造方法
	 * 
	 * @param clazz
	 * @param parameterTypes
	 * @param initargs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<?> clazz, Class<?>[] parameterTypes, Object[] initargs) {
		T obj = null;
		try {
			Constructor<?> s;
			if (parameterTypes != null) {
				s = clazz.getConstructor(parameterTypes);
				obj = (T) s.newInstance(initargs);
			} else {
				s = clazz.getConstructor();
				obj = (T) s.newInstance();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
