package com.bc.config;
/*package com.qbb.config;

import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  

import com.qbb.data.dao.BaseDao;
import com.qbb.data.dao.impl.BaseDaoImpl;
import com.qbb.data.util.DBUtil;
import com.qbb.util.db.IBaseService;
  
@Configuration  
public class SpringConfig {  
  
    @Bean(name = "appDbUtil")   
    public DBUtil dbUtil(){  
        return  new DBUtil();  
    }  
    
    @Bean(name = "developDbUtil") 
    public BaseDao foo() {
       return new BaseDaoImpl(dbUtil());
    }
    @Bean
    public Bar bar() {
       return new Bar();
    }
}  

package com.baobaotao.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//①将一个POJO标注为定义Bean的配置类
@Configuration
public class AppConf {
        //②以下两个方法定义了两个Bean，以提供了Bean的实例化逻辑
    @Bean
    public UserDao userDao(){
       return new UserDao();    
    }
    
    @Bean
    public LogDao logDao(){
        return new LogDao();
    }
    //③定义了logonService的Bean
    @Bean
    public LogonService logonService(){
        LogonService logonService = new LogonService();
                //④将②和③处定义的Bean注入到LogonService Bean中
        logonService.setLogDao(logDao());
        logonService.setUserDao(userDao());
        return logonService;
    }
}*/