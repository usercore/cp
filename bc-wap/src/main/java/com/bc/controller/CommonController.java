package com.bc.controller;

import com.bc.http.MyHttpUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommonController extends BaseController {

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected MyHttpUtil myHttpUtil;

	@RequestMapping("/")
	public String home() {
		return "homePage";
	}
	/**
	 * 普通页面跳转
	 * @return
	 */
	@RequestMapping("/commonPage")
	public String commonPage() {
		String page = request.getParameter("page");
		HttpSession session = request.getSession();
		Object userId = session.getAttribute("u");
		if(!notLoginPage.contains(page) && userId == null){
			return "login";
		}
		return page;
	}

	/**
	 * 通用的返回Json数据的模板
	 *
	 * @throws IOException
	 */
	@RequestMapping(value = "/commonData", method = RequestMethod.POST)
	@ResponseBody
	public void commonData() throws IOException {

		HttpSession session = request.getSession();
		Object userId = session.getAttribute("u");

		Map<String, String> param = new HashMap<String, String>();
		
		Enumeration<String> keys = request.getParameterNames();
		
		while (keys.hasMoreElements()) {
			String key = keys.nextElement().toString();
			param.put(key, request.getParameter(key));
		}

		JSONObject json = new JSONObject();
		if (!notLoginAction.contains(param.get("actina"))) {
			if (userId == null) {
				json.put("erorcd", "700002");
				json.put("errmsg", "用户未登录或登录状态已失效");
				printObject(json);
				return;
			}else{
				param.put("userId", userId.toString());
			}
		}

		try {
			String actina = param.get("actina");
			String isNeedImgCode = param.get("isNeedImgCode");
			System.out.println("isNeedImgCode" + isNeedImgCode);
			if (isNeedImgCode == null || isNeedImgCode.equals("")) {
				isNeedImgCode = "1";
			}
			if (actina.equals("getPhoneCode") && isNeedImgCode.equals("1")) {
				String txCode = param.get("txCode");
				String code = (String) session.getAttribute("userlogin_checkCode");
				if (txCode == null || txCode.equals("")) {
					json.put("msg", "请输入图形验证码");
					json.put("errmsg", "请输入图形验证码");
					printObject(json);
					return;
				}
				if (!txCode.equals(code)) {
					json.put("msg", "图形验证码错误");
					json.put("errmsg", "图形验证码错误");
					printObject(json);
					return;
				}
				json.put("msg", "ok");

			}

			JSONObject result = myHttpUtil.sendPost(param, request);
			printObject(result);

			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		json.put("erorcd", "9527");
		json.put("errmsg", "提交失败");
		printObject(json);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public void login() throws IOException {

		HttpSession session = request.getSession();

		Map<String, String> param = new HashMap<String, String>();

		String userPhone = request.getParameter("userPhone");
		String password = request.getParameter("password");
		param.put("actina", "userLogin");
		param.put("userPhone", userPhone);
		param.put("password", password);

		JSONObject result = myHttpUtil.sendPost(param, request);

		if (result.get("erorcd").equals("000000")) {
			session.setAttribute("u", result.get("userId"));
		}
		printObject(result);
	}

	/**
	 * 用户注册
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	@ResponseBody
	public void register() throws IOException {

		HttpSession session = request.getSession();

		Map<String, String> param = new HashMap<String, String>();

		param.put("actina", "userRegister");
		param.put("userPhone", request.getParameter("userPhone"));
		param.put("password", request.getParameter("password"));
		param.put("nickName", request.getParameter("nickName"));

		JSONObject result = myHttpUtil.sendPost(param, request);

		if (result.get("erorcd").equals("000000")) {
			session.setAttribute("u", result.get("userId"));
		}
		printObject(result);
	}
	
	/**
	 * 退出登录
	 * @throws IOException 
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST)
	@ResponseBody
	public void loginOut() throws IOException{
		JSONObject json = new JSONObject();
		HttpSession session = request.getSession();
		session.removeAttribute("u");
		json.put("erorcd", "000000");
		json.put("errmsg", "退出成功");
		printObject(json);
	} 
}
