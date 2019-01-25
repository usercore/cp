/**
 * 
 */
package com.ivan.util.bc;

import static org.junit.Assert.fail;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import static com.ivan.StringUtil.*;
/**
 *
 */
@ContextConfiguration({ "classpath:applicationProvider.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class DBGenerator {

	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	private Configuration cfg;
	public static String outputPath = new File("").getAbsolutePath() + "\\gen\\";

	
	String[] templateNames = new String[] { "domain.ftl", "mapperJava.ftl",  "mapperXml.ftl","service.ftl","serviceImpl.ftl" };
	String[] outputPrepath = new String[] { "domain", "dao",  "dao","service","service" };
	String[] outputPostfix = new String[] { "*.java", "*Mapper.java",  "*Mapper.xml" ,"I*Service.java","*ServiceImpl.java"};
	
	@Before
	public void bf() throws Throwable {
		cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(DBGenerator.class.getResource("").getFile()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
	}

	// 获取需要生成代码的表
	private Element getTableCfgs() throws Throwable {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(DBGenerator.class.getResourceAsStream("gencfg.xml"));
		return doc.getRootElement();
	}

	// 获取表的基本信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map getTableMetadata(Element t) {
		Map root = new HashMap();
		String tableName = t.attributeValue("name");
		try {
			Connection n = sqlSessionFactory.getObject().openSession().getConnection();
			PreparedStatement s = n.prepareCall("select * from " + tableName);

			DatabaseMetaData dbMeta = n.getMetaData();
			ResultSet pk = dbMeta.getPrimaryKeys(null, null, tableName.toUpperCase());
			Set<String> kset = new HashSet<String>();
			while (pk.next()) {
				kset.add(pk.getString(4).toLowerCase());
			}

			ResultSet r = s.executeQuery();

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			List<Map<String, String>> list_pk = new ArrayList<Map<String, String>>();
			List<Map<String, String>> list_npk = new ArrayList<Map<String, String>>();
			ResultSetMetaData meta = r.getMetaData();
			int cnt = meta.getColumnCount();
			for (int i = 1; i <= cnt; i++) {
				Map<String, String> m = new HashMap<String, String>();
				m.put("columnName", meta.getColumnName(i));
				m.put("property", replaceUnderlineAndfirstToUpper(meta.getColumnName(i),"_",""));
				m.put("columnRawType", meta.getColumnTypeName(i));
				m.put("columnType", fixType(meta.getColumnType(i)));
				m.put("columnJdbcType", fixType2(meta.getColumnType(i)));
				m.put("columnClass", meta.getColumnClassName(i));
				m.put("columnScale", meta.getScale(i) + "");
				m.put("columnPrecision", meta.getPrecision(i) + "");
				list.add(m);
				if (kset.contains(meta.getColumnName(i).toLowerCase())) {
					list_pk.add(m);
				} else {
					list_npk.add(m);
				}
			}
			root.put("package", t.attributeValue("package"));
			root.put("tableName", t.attributeValue("name"));
			root.put("primaryKey", t.attributeValue("primaryKey"));
			root.put("primaryKeyPro", replaceUnderlineAndfirstToUpper(t.attributeValue("primaryKey"),"_",""));
			root.put("fixName", firstToUpperAndReplaceLine(t.attributeValue("name"),"_"));
			root.put("fixNameLower", replaceUnderlineAndfirstToUpper(t.attributeValue("name"),"_",""));
			root.put("meta", list);
			root.put("meta_pk", list_pk);
			root.put("meta_npk", list_npk);

			try {
				r.close();
			} catch (Exception e) {
			}
			try {
				s.close();
			} catch (Exception e) {
			}
			try {
				n.close();
			} catch (Exception e) {
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}

		return root;
	}

	// 填充到模板，并生成文件
	@SuppressWarnings("rawtypes")
	private void fillTemplate(Map root, String templateName, String outputPrepath, String outputPostfix) throws Throwable {
		Template template = cfg.getTemplate(templateName);
		String packageName = root.get("package").toString();
		if (!new File(outputPath + packageName +"//" + outputPrepath).exists()) {
			if (!new File(outputPath + packageName ).exists()){
				new File(outputPath + packageName).mkdir();
			}
			new File(outputPath + packageName +"//" + outputPrepath).mkdir();
		}
		String fileName = outputPostfix.replace("*", root.get("fixName").toString());
		Writer out = new FileWriter(outputPath + packageName +"//" + outputPrepath + "//" + fileName);
		template.process(root, out);
		out.flush();
	}


	// 处理类型转换
	private String fixType(int type) {
		switch (type) {
		case Types.BIGINT:
			return "Long";
		case Types.VARCHAR:
			return "String";
		case Types.CHAR:
			return "String";
		case Types.CLOB:
			return "String";
		case Types.INTEGER:
			return "Integer";
		case Types.NUMERIC:
			return "Integer";
		case Types.FLOAT:
			return "BigDecimal";
		case Types.DOUBLE:
			return "BigDecimal";
		case Types.DECIMAL:
			return "BigDecimal";
		case Types.DATE:
			return "Date";
		case Types.TIMESTAMP:
			return "Date";
		case Types.LONGVARCHAR:
			return "String";
		default:
			return "unsupported";
		}
	}

	// 处理类型转换
	private String fixType2(int type) {
		switch (type) {
		case Types.BIGINT:
			return "BIGINT";
		case Types.VARCHAR:
			return "VARCHAR";
		case Types.CHAR:
			return "CHAR";
		case Types.CLOB:
			return "CLOB";
		case Types.INTEGER:
			return "INTEGER";
		case Types.NUMERIC:
			return "NUMERIC";
		case Types.FLOAT:
			return "FLOAT";
		case Types.DOUBLE:
			return "DOUBLE";
		case Types.DECIMAL:
			return "DECIMAL";
		case Types.DATE:
			return "DATE";
		case Types.TIMESTAMP:
			return "TIMESTAMP";
		case Types.LONGVARCHAR:
			return "TEXT";
		case Types.TINYINT:
			return "TINYINT";
		default:
			return "unsupported";
		}
	}

	

	// 入口
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gen() throws Throwable {
		List<Element> tableCfgs = getTableCfgs().elements("table");
		for (Element t : tableCfgs) {
			Map column = getTableMetadata(t);
			for (int i = 0; i < templateNames.length; i++) {
				fillTemplate(column, templateNames[i], outputPrepath[i], outputPostfix[i]);
			}
		}
	}

	@Test
	public void main() {
		try {
			gen();
			System.out.println("代码生成完成");
		} catch (Throwable e) {
			e.printStackTrace();
			fail();
		}
	}

}
