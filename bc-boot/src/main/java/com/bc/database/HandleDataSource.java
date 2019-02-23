package com.bc.database;


/**  
* 保存当前线程数据源的key  
* @author   
* @version 1.0  
*  
*/  
public class HandleDataSource {  
   public static final ThreadLocal<String> holder = new ThreadLocal<String>();  
     
   /**  
    * 绑定当前线程数据源路由的key     
    * @param key  
    */  
   public static void putDataSource(String datasource) {  
       holder.set(datasource);  
   }  
     
   /**  
    * 获取当前线程的数据源路由的key  
    * @return  
    */  
   public static String getDataSource() {  
       return holder.get();  
   }      
}  