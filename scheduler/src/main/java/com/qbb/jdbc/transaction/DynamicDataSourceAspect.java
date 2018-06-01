package com.qbb.jdbc.transaction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.qbb.jdbc.datasource.DynamicDateSourceHolder;
import com.qbb.jdbc.transaction.annotation.MasterDataSource;
import com.qbb.jdbc.transaction.annotation.ReadOnlyDataSource;
import com.qbb.jdbc.transaction.annotation.SlaveDataSource;

public class DynamicDataSourceAspect {

	/**
	 * 方法调用之前设置数据源
	 * 
	 * @param point
	 */
	public void before(JoinPoint point) {
		Annotation annotation = this.getMethodAnnotation(point);
		if (annotation == null) {
			annotation = getClassAnnotation(point);
		}
		if (annotation == null) {
			DynamicDateSourceHolder.useDefaultDataSource();
			return;
		}
		if (annotation instanceof MasterDataSource) {
			DynamicDateSourceHolder.useMasterDataSource();
		} else if (annotation instanceof SlaveDataSource) {
			DynamicDateSourceHolder.useSlaveDataSource();
		} else if (annotation instanceof ReadOnlyDataSource) {
			DynamicDateSourceHolder.useReadOnlyDataSource();
		} else {
			DynamicDateSourceHolder.useDefaultDataSource();
		}
	}

	private Annotation getMethodAnnotation(JoinPoint point) {
		Annotation annotation = null;
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		Class<? extends Annotation> a = getAnnotationPresent(method);
		if (a != null) {
			annotation = method.getAnnotation(a);
		} else {
			Class<?> clazz = point.getTarget().getClass();
			method = this.getMethod(clazz, method.getName(), method.getParameterTypes());
			a = getAnnotationPresent(method);
			if (method != null && a != null) {
				annotation = method.getAnnotation(a);
			}
		}
		return annotation;
	}

	private Annotation getClassAnnotation(JoinPoint point) {
		Annotation annotation = null;
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Class<?> clazz = methodSignature.getDeclaringType();
		Class<? extends Annotation> a = this.getAnnotationPresent(clazz);
		if (a != null) {
			annotation = clazz.getAnnotation(a);
		} else {
			clazz = point.getTarget().getClass();
			annotation = getClassAnnotation(clazz);
		}
		return annotation;
	}

	private Annotation getClassAnnotation(Class<?> clazz) {
		Annotation annotation = null;
		Class<? extends Annotation> a = this.getAnnotationPresent(clazz);
		if (a != null) {
			annotation = clazz.getAnnotation(a);
		} else {
			if (clazz.getSuperclass() != null) {
				return this.getClassAnnotation(clazz.getSuperclass());
			}
		}
		return annotation;
	}

	private Class<? extends Annotation> getAnnotationPresent(Method method) {
		Class<? extends Annotation> a = null;
		if (method.isAnnotationPresent(MasterDataSource.class)) {
			a = MasterDataSource.class;
		} else if (method.isAnnotationPresent(SlaveDataSource.class)) {
			a = SlaveDataSource.class;
		} else if (method.isAnnotationPresent(ReadOnlyDataSource.class)) {
			a = ReadOnlyDataSource.class;
		}
		return a;
	}

	private Class<? extends Annotation> getAnnotationPresent(Class<?> clazz) {
		Class<? extends Annotation> a = null;
		if (clazz.isAnnotationPresent(MasterDataSource.class)) {
			a = MasterDataSource.class;
		} else if (clazz.isAnnotationPresent(SlaveDataSource.class)) {
			a = SlaveDataSource.class;
		} else if (clazz.isAnnotationPresent(ReadOnlyDataSource.class)) {
			a = ReadOnlyDataSource.class;
		}
		return a;
	}

	private Method getMethod(Class<?> clazz, String methodName, Class<?>... types) {
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(methodName, types);
		} catch (NoSuchMethodException e) {
			if (clazz.getSuperclass() != null) {
				return getMethod(clazz.getSuperclass(), methodName, types);
			} else {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return method;
	}

}
