package com.qbb.jdbc.transaction.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 使用只读数据源，可配置多个，详细参考DynamicDataSource
 * 
 *
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public @interface ReadOnlyDataSource {
}
