package com.qbb;



import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
  
/** 
 * @author  
 * @version 1.0 
 * @since 1.0 
 */  
@SuppressWarnings("deprecation")
@ContextConfiguration ({ "applicationContext-jdbc.xml"})  
@RunWith(SpringJUnit4ClassRunner.class)  
@Transactional  
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)  
public abstract class AbstractTestCase{  
  
} 