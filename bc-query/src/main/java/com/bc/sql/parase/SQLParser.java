package com.bc.sql.parase;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.scripting.xmltags.XMLScriptBuilder;
import org.apache.ibatis.session.Configuration;

import com.bc.sql.parase.domain.BcBoundSql;


public class SQLParser {

	String sqlsource;
	String xml_template = "<select parameterType=\"java.util.Map\">%s</select>";

	public SQLParser(String sqlsource) {

		if (sqlsource == null) {
			throw new NullPointerException("sql source is null.");
		}
		this.sqlsource = sqlsource;
	}

	public BcBoundSql getBoundSql() {
		return getBoundSql(null);
	}

	public  BcBoundSql getBoundSql(Map<String, Object> parameterObject) {
		BcBoundSql bcBoundSql = new BcBoundSql();
		
		String xml = String.format(this.xml_template, this.sqlsource);

		XPathParser xpathParser = new XPathParser(xml);
		XNode xNode = xpathParser.evalNode("select");
		Configuration configuration = new Configuration();
		XMLScriptBuilder builder = new XMLScriptBuilder(configuration, xNode);
		SqlSource sqlSource = builder.parseScriptNode();
		BoundSql boundSql = sqlSource.getBoundSql(parameterObject);

		bcBoundSql.setSql(boundSql.getSql());
		
		List<ParameterMapping> params = boundSql.getParameterMappings();
		
		Object[] obj = new Object[params.size()];
		
		for (int i = 0; i < params.size(); i++) {
			obj[i] = parameterObject.get(params.get(i).getProperty());
		}
		bcBoundSql.setParams(obj);
		
		return bcBoundSql;
	}

}
