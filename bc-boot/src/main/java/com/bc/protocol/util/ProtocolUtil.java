package com.bc.protocol.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bc.common.constant.IPCConstans;
import com.bc.encrypt.RsaUtil;
import com.bc.reponse.ErrorCode;
import com.bc.session.User;
import com.bc.util.AppFunction;
import com.bc.util.VersionUtil;
import com.bc.util.exception.BusinessException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.stream.FactoryConfigurationError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ProtocolUtil {
	private static final Logger log = LoggerFactory.getLogger(ProtocolUtil.class);


	/**
	 * 验证请求数据 取得请求报文主体 因考虑请求参数可能为多条记录，所以返回记录集。
	 * 
	 * 单条请求报文格式（交易配置文件）： <Request> <Field name="custtp" title="****"
	 * type="String" /> <Field name="custna" title="****" type="String" />
	 * <Field name="certtp" title="****" type="String" /> <Field name="certno"
	 * title="****" type="String" /> </Request>
	 * 
	 * 多条请求报文格式（交易配置文件）： 此格式只能有一个Field，且声明multi属性。 <Request> <Field
	 * name="culist" title="" multi=""> <Record> <Field name="custno"
	 * title="客户号" type="String" maxlen="10" defaultval="0000000000"/> </Record>
	 * </Field> </Request>
	 * 
	 * 注：如存在多个Field节点，则一定要保证每个节点都不存在multi属性。 错误格式： <Request> <Field
	 * name="custtp" title="****" type="String" /> <Field name="custna"
	 * title="****" type="String" multi="" /> <Field name="certtp" title="****"
	 * type="String" /> <Field name="certno" title="****" type="String" />
	 * </Request>
	 * 
	 * 请求参数以Map对象存放 以配置文件的为准
	 * 多条记录先组装一个DataObject对象，再逐条压入一个List中，然后再根据Request报文格式中指定的名字，put至HashMap中
	 * 
	 * @param prcsCfg
	 *            交易配置文件信息
	 * @param reqDataMap
	 *            请求参数集
	 * @param paraMap
	 *            Session参数集
	 * @return 请求报文主体
	 * @throws FactoryConfigurationError
	 * @throws Exception
	 */
	public static Map<String, Object> verifyRequest(CommunicationConfigNew prcsCfg, HttpServletRequest request) throws BusinessException {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 创建一个OMElement对象
		// 取得参数配置列表
		List<Element> fieldList = prcsCfg.getReqEleLs();
		String actina = prcsCfg.getActina();

		// 交易名称
		String descna = prcsCfg.getDesc();
		
		

		// 定义钱爸爸格式报文
		StringBuffer message = new StringBuffer();

		// 定义URL格式报文，方便调试
		StringBuffer urlString = new StringBuffer();

		UUID transq = UUID.randomUUID();
		log.info("transq_rep:" + transq.toString());

		message.append(" requcd=000000|requsg=请求验证成功|transq=" + transq + "|actina=" + actina + "|descna=" + descna + "|");

		urlString.append("?actina=" + actina + "&");


		// 渠道
		String channel = request.getParameter("channel");
		
		String app_version = request.getParameter("app_version");
		
		boolean isCheckResubmit = false;
		if(VersionUtil.isLastVersion(IPCConstans.check_last_version, app_version)) {
			// 是否验证重复提交
			String isNeedToken = prcsCfg.getIsNeedToken();
			// 是否验证重复提交标识
			if (StringUtils.isNotEmpty(isNeedToken)) {
				if (!"WAP".equals(channel) && IPCConstans.isNeedToken_true.equals(isNeedToken)) {
					isCheckResubmit = true;
				}
			}
		}
		
		
		for (Element reqFiledEle : fieldList) {
			// 取得参数名称
			String fieldName = reqFiledEle.getAttributeValue("name");
			
			// 配置不匹配错误字符串
			// 值
			String elText = null;
			// 如果配置文件字段在map中有值，则封装，如果没有 将NULL 或 默认值封装进去
			if (request.getParameter(fieldName) != null) {
				elText = request.getParameter(fieldName);

				String input = reqFiledEle.getAttributeValue("input");// 必填/可选
				String length = reqFiledEle.getAttributeValue("length");// 长度
				String type = reqFiledEle.getAttributeValue("type");// 类型
				String title = reqFiledEle.getAttributeValue("title");

				String ismoney = reqFiledEle.getAttributeValue("ismoney");// 金额控制属性

				String usernull = reqFiledEle.getAttributeValue("usernull");
				
//				if(!stringFilter(elText)){
//					throw new BusinessException("800002", "非法数据，请重新输入");
//				}
				
				if ("false".equals(usernull) && !"WEBAPP".equals(channel)) {
					// 必须校验userId 是否登录
					String sessionUserIds = "";
					User user = (User) request.getSession().getAttribute("user");
					if (user != null) {
						sessionUserIds = String.valueOf(user.getId());
					}
					System.out.println("elText  =  " + elText);
					elText = PWD.dec(elText);
					if (!elText.equals(sessionUserIds)) {
						log.info(sessionUserIds + "<<==用户登录ID检查==>>" + elText);
						throw new BusinessException(ErrorCode.userNotLogin.getErrorCode(), ErrorCode.userNotLogin.getErrorMessage());
					}
				}else if("true".equals(usernull) && elText!=null && !elText.equals("") && !elText.equals("-1") && !"WEBAPP".equals(channel)){
					String sessionUserIds = "";
					User user = (User) request.getSession().getAttribute("user");
					if (user != null) {
						sessionUserIds = String.valueOf(user.getId());
					}
					System.out.println("elText  =  " + elText);
					elText = PWD.dec(elText);
					if (!elText.equals(sessionUserIds)||elText.equals("-1")) {
						elText = "";
					}
				}
				
				if ("String".equals(type)) {
					int elTextLength = elText.length();// 传入参数值
					int cfglength = Integer.parseInt(length);// 配置文件长度
					if (elTextLength > cfglength) {
						throw new BusinessException("700001", title + "最大长度为" + cfglength + ",输入参数长度过长" + elTextLength);
					}
				}
				
				log.info("elText : " + elText);
				
				if ("M".equals(input)) {
					if (elText == null || "".equals(elText)) {
						throw new BusinessException("888888", "请输入" + title);
					}
				}
				
				if (VersionUtil.isLastVersion(IPCConstans.check_last_version, app_version)) { // 兼容老版本
					if ("true".equals(reqFiledEle.getAttributeValue("isNeedDecrypt"))&&!"-1".equals(elText)) {
						try {
							elText = elText.replace("%2B", "+");
							elText = elText.replace("%2F", "/");
							elText = elText.replace("%3F", "?");
							elText = elText.replace("%23", "#");
							elText = elText.replace("%26", "&");
							elText = elText.replace("%3D", "=");
							elText = elText.replace("%20", " ");
							elText = elText.replace("%25", "%");
							elText = RsaUtil.decryptByPriKey(elText, RsaUtil.pri_key);
						} catch (Exception e) {
							
							log.info(e.getMessage(), e);
							throw new BusinessException("700002", "密码错误");

						}
					}

					if (isCheckResubmit) { // 验证重复提交
						if (IPCConstans.token_key.equals(fieldName)) {
							HttpSession session = request.getSession();
							// 获取当前token
							Object need_token_new = session.getAttribute(IPCConstans.needToken_new_key);
							Object need_token_old = session.getAttribute(IPCConstans.needToken_old_key);
							log.info("need_token_new :" + need_token_new);
							log.info("need_token_old :" + need_token_old);
							log.info("elText :" + elText);
							if (elText.equals(need_token_new.toString())) {
								session.setAttribute(IPCConstans.needToken_new_key, System.currentTimeMillis()); // 设置新的token
								session.setAttribute(IPCConstans.needToken_old_key, need_token_new); // 保存上一次的token
							} else {
								// 获取上次的token
								if (elText.equals(need_token_old)) {
									throw new BusinessException("800003", "请勿重复提交");
								} else {
									throw new BusinessException("800004", "token验证失败");
								}
								
							}
						}
					}

				}

				// 不带小数点
				if ("Y".equals(ismoney)) {
					boolean isNumeric = AppFunction.isNumeric(elText);
					if (!isNumeric) {
						throw new BusinessException("800001", "输入金额需为整数，请重新输入");
					}
				}

				// 带小数点
				if ("D".equals(ismoney)) {
					boolean isNumeric = AppFunction.isDecimal(elText);
					if (!isNumeric) {
						throw new BusinessException("800002", "非法金额，请重新输入");
					}
				}

				urlString.append(fieldName + "=" + elText + "&");

				// 组装Element加入报文主体节点
				message.append(fieldName + "=" + elText + "|");

				
				
			} else {
				// 加入报文主体节点
				/* 默认值：当没有取到值时，就将值设为默认值 */
				String defaultValue = reqFiledEle.getAttributeValue("default");
				String input = reqFiledEle.getAttributeValue("input");
				String title = reqFiledEle.getAttributeValue("title");
				if ("M".equals(input)) {
					if (elText == null || "".equals(elText)) {
						throw new BusinessException("888888", "请输入" + title);
						
					}
				}

				if (elText == null) {
					elText = defaultValue != null ? defaultValue : "";
				}

				urlString.append(fieldName + "=" + elText + "&");

				
				message.append(fieldName + "=" + elText + "|");
			}
			String newName = reqFiledEle.getAttributeValue("newname");
			if(newName!=null&&!newName.equals("")){
				returnMap.put(newName, elText);
			}else{
				returnMap.put(fieldName, elText);
			}
			
		}

		log.info("配置参数报文:" + message.toString());

		log.info("URL格式报文:" + urlString.toString());

		return returnMap;
	}
	// 只允许字母和数字
	// String regEx = "[^a-zA-Z0-9]";
	// 清除掉所有特殊字符
	private static boolean stringFilter(String str) throws PatternSyntaxException {
		String regEx = "[`~!@$^&*()={}':;',\\[\\]<>~！@￥……&*（）{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim().equals(str);
	}
	/**
	 * 将多个Map对象转换为JSON格式字符串返回
	 * @param hashMap
	 * @param dbListMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String setResponseService(CommunicationConfig prcsCfg, Map<String, Object> dbMapListMap) {

		Map<String, Object> hashMap = new HashMap<String, Object>();
		StringBuffer jsonString = new StringBuffer();

		try {
			if (dbMapListMap != null) {
				// 获取一级Filed所有节点
				List<Element> fieldFatherList = prcsCfg.getRspEleLs();

				// 对一级Filed节点进行循环处理
				for (Element rspFieldFatherdEle : fieldFatherList) {

					JSONArray jsonDbListMap = new JSONArray();

					// 获取一级节点名称
					String fieldName = rspFieldFatherdEle.getAttributeValue("name");
					// 根据配置文件配置节点 与 手动封装的节点继续匹配 已配置为准

					if (dbMapListMap.containsKey(fieldName)) {

						Object obj = dbMapListMap.get(fieldName);// 值

						if (obj instanceof List) {

							List<Map<String, Object>> dbListMap = (List<Map<String, Object>>) obj;
							// 节点下有子节点（处理二级节点）
							if (rspFieldFatherdEle.getChildren() != null && rspFieldFatherdEle.getChildren().size() > 0) {
								List<Element> fieldList = rspFieldFatherdEle.getChildren("Field");

								List<Map<String, Object>> returnListMap = MessageUtil.getResponseMessage(fieldList, dbListMap);

								jsonDbListMap.addAll(returnListMap);
								hashMap.put(fieldName, jsonDbListMap);

								hashMap.put("pagect", dbListMap.size() + "");// 记录数

							} else {
								// 节点下有无节点（处理一级节点）
								List<Map<String, Object>> returnListMap = MessageUtil.getResponseMessage(fieldFatherList, dbListMap);

								jsonDbListMap.addAll(returnListMap);
								hashMap.put(fieldName, jsonDbListMap);

								hashMap.put("pagect", dbListMap.size() + "");// 记录数

							}
						} else {
							hashMap.put(fieldName, obj);
						}

					} else {
						log.info("配置文件中键值为:" + fieldName);
						hashMap.put("pagect", "0");// 记录数
					}
				}
			} else {
				hashMap.put("pagect", "0");// 记录数
			}
			jsonString.append(JSONObject.fromObject(hashMap).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString.toString();

	}
	
	/**
	 * 将多个Map对象转换为JSON格式字符串返回
	 * @param hashMap
	 * @param dbListMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String setResponseService(CommunicationConfigNew prcsCfg, Map<String, Object> dbMapListMap) {

		Map<String, Object> hashMap = new HashMap<String, Object>();
		StringBuffer jsonString = new StringBuffer();

		try {
			if (dbMapListMap != null) {
				// 获取一级Filed所有节点
				List<Element> fieldFatherList = prcsCfg.getRspEleLs();

				// 对一级Filed节点进行循环处理
				for (Element rspFieldFatherdEle : fieldFatherList) {

					JSONArray jsonDbListMap = new JSONArray();

					// 获取一级节点名称
					String fieldName = rspFieldFatherdEle.getAttributeValue("name");
					// 根据配置文件配置节点 与 手动封装的节点继续匹配 已配置为准

					if (dbMapListMap.containsKey(fieldName)) {

						Object obj = dbMapListMap.get(fieldName);// 值

						if (obj instanceof List) {

							List<Map<String, Object>> dbListMap = (List<Map<String, Object>>) obj;
							// 节点下有子节点（处理二级节点）
							if (rspFieldFatherdEle.getChildren() != null && rspFieldFatherdEle.getChildren().size() > 0) {
								List<Element> fieldList = rspFieldFatherdEle.getChildren("Field");

								List<Map<String, Object>> returnListMap = MessageUtil.getResponseMessage(fieldList, dbListMap);

								jsonDbListMap.addAll(returnListMap);
								hashMap.put(fieldName, jsonDbListMap);

								hashMap.put("pagect", dbListMap.size() + "");// 记录数

							} else {
								// 节点下有无节点（处理一级节点）
								List<Map<String, Object>> returnListMap = MessageUtil.getResponseMessage(fieldFatherList, dbListMap);

								jsonDbListMap.addAll(returnListMap);
								hashMap.put(fieldName, jsonDbListMap);

								hashMap.put("pagect", dbListMap.size() + "");// 记录数

							}
						} else {
							hashMap.put(fieldName, obj);
						}

					} else {
						log.info("配置文件中键值为:" + fieldName);
						hashMap.put("pagect", "0");// 记录数
					}
				}
			} else {
				hashMap.put("pagect", "0");// 记录数
			}
			hashMap.put("erorcd", dbMapListMap.get("erorcd"));
			hashMap.put("errmsg", dbMapListMap.get("errmsg"));
			jsonString.append(JSONObject.fromObject(hashMap).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString.toString();

	}

	public static String getBaseUrl(HttpServletRequest request) {
		String getServletPath = request.getServletPath();// main
		String getRequestURL = request.getRequestURL().toString();// http://localhost:8080/ServletTest/main/index/testpage/test
		String baseUrl = getRequestURL.substring(0, getRequestURL.indexOf(getServletPath));
		return baseUrl;
	}
}
