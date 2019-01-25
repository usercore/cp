package com.ivan.util.initSql;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

@SuppressWarnings("unchecked")
public class InitSqlMapAction {
	/**
	 * 
	* @Author:lizk
	* @CreateDate 2011-8-21 ����02:17:10
	* @Description: ��ʼ��SQLMap
	* @return
	* @throws InvalidConfigurationException
	* @throws SQLException
	* @throws InterruptedException  
	* @return String 
	* @throws
	 */
	@Test
	public void init() throws InvalidConfigurationException, SQLException, InterruptedException {
		String result = "success";
		try {
		List<String> warnings = new ArrayList<String>();
		   boolean overwrite = true;
		   //String file= "E:/Ivan/项目/ticket/code/ticket_code/opps-admin/target/test-classes/generator.xml";
		   String file="src/test/java/generator.xml";
		   File configFile = new File(file);  
		   String path=configFile.getAbsolutePath();
		   ConfigurationParser cp = new ConfigurationParser(warnings);
		   org.mybatis.generator.config.Configuration  config = cp.parseConfiguration(configFile);
		   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		   myBatisGenerator.generate(null);
		
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (XMLParserException e1) {
			e1.printStackTrace();
		}
	}
}
