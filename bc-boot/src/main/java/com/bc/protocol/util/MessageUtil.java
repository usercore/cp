package com.bc.protocol.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Description:
 * 
 * @author: hm
 * @Company: qianbaba
 * @E-mail: hanmin@qian88.com.cn
 * @version 创建时间：2014-8-7 下午01:15:56
 */
public class MessageUtil {

	protected  final Logger  log = LoggerFactory.getLogger(this.getClass()); 

	

	/**
	 * 
	 * @param paraMap
	 *            session字段
	 * @param message
	 *            定义钱爸爸格式报文
	 * @return
	 */
	public static StringBuffer getMapMessage(Map<String, String> paraMap) {
		StringBuffer message = new StringBuffer();
		Iterator iterator = paraMap.keySet().iterator();
		while (iterator.hasNext()) {
			String name = (String) iterator.next();
			String value = paraMap.get(name).toString();
			message.append(name + "=" + value + "|");

		}
		return message;
	}

	/**
	 * 根据配置文件 封装消息
	 * 
	 * @param fieldList
	 * @param rspDataMap
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getResponseMessage(
			List<Element> fieldList, List<Map<String, Object>> rspDataMap)
			throws Exception {
		List<Map<String, Object>> returnListMap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < rspDataMap.size(); i++) {
			Map<String, Object> fieldMap = rspDataMap.get(i);
			Map<String, Object> addMap = new HashMap<String, Object>();
			for (Element rspFiledEle : fieldList) {
				// 取得参数名称
				String fieldName = rspFiledEle.getAttributeValue("name");
				String newName = rspFiledEle.getAttributeValue("newname");
				// System.out.println("fieldName = "+fieldName+" newName = "+newName);
				if (newName != null && newName.trim().length() > 0) {
					String fieldType = rspFiledEle.getAttributeValue("type");
					Object elText = fieldMap.get(newName);// 值
					if (elText == null || "null".equals(elText)) {
						// app IOS判断null值比较麻烦
						elText = "";
					} else {
						elText = elText.toString();
					}

					if("".equals(elText)){
						
					}else{

						/*if ("Double".equals(fieldType)) {
							elText = AppFunction.formatMoney(elText, 2);
						}*/

						addMap.put(fieldName, elText);
						continue;
					}
					
				}  
				if (fieldMap.containsKey(fieldName)) {
					String fieldType = rspFiledEle.getAttributeValue("type");
					Object elText = fieldMap.get(fieldName);// 值
					if (elText == null || "null".equals(elText)) {
						// app IOS判断null值比较麻烦
						elText = "";
					} else {
						elText = elText.toString();
					}

					/*if ("Double".equals(fieldType)) {
						elText = AppFunction.formatMoney(elText, 2);
					}
*/
					addMap.put(fieldName, elText);
				}
			}
			returnListMap.add(addMap);
		}
		return returnListMap;
	}

}
