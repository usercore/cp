package com.bc.database;

import java.lang.reflect.Method;  
import org.aspectj.lang.JoinPoint;  
  
import org.aspectj.lang.reflect.MethodSignature;  
  
  
/**  
 * 执行dao方法之前的切面  
 * 获取datasource对象之前往HandleDataSource中指定当前线程数据源路由的key  
 * @author Administrator  
 *  
 */  
public class DataSourceAspect {  
      
     
    /**  
     * 在dao层方法之前获取datasource对象之前在切面中指定当前线程数据源路由的key  
     */  
     public void before(JoinPoint point)  
        {         
              
              
             Object target = point.getTarget();  
             System.out.println(target.toString());  
             String method = point.getSignature().getName();  
             System.out.println(method);  
             Class<?>[] classz = target.getClass().getInterfaces();  
             Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())  
                     .getMethod().getParameterTypes();  
             try {  
                 Method m = classz[0].getMethod(method, parameterTypes);  
                 System.out.println(m.getName());  
                 if (m != null && m.isAnnotationPresent(DataSource.class)) {  
                     DataSource data = m.getAnnotation(DataSource.class);  
                     System.out.println("用户选择数据库库类型："+data.value());  
                     HandleDataSource.putDataSource(data.value());  
                 }else{
                	 System.out.println("用户选择数据库库类型：write");  
                	 HandleDataSource.putDataSource("write");  
                 }
                   
             } catch (Exception e) {  
                 e.printStackTrace();  
             }  
        }  
}  