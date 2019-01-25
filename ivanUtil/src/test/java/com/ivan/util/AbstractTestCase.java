package com.ivan.util;




import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
  
/** 
 * @author  
 * @version 1.0 
 * @since 1.0 
 */  
@ContextConfiguration ({ "applicationContext.xml"})  
@RunWith(SpringJUnit4ClassRunner.class)  
public abstract class AbstractTestCase{  
  
} 