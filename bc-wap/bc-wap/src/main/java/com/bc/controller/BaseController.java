package com.bc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
public class BaseController {
	
	protected static Set<String> notLoginAction = new HashSet<String>();
	static{
		notLoginAction.add("getCurrentIssue");
		notLoginAction.add("getBasicPlotData");
		notLoginAction.add("getCompositeChartData");
		notLoginAction.add("getM3PlotData");
		notLoginAction.add("getPatternsChartData");
		
	}
	
	protected static Set<String> notLoginPage = new HashSet<String>();
	static{
		notLoginPage.add("homePage");
		notLoginPage.add("user/register");
		notLoginPage.add("trendchart/k3/basic");
	}

	@Autowired  
	protected  HttpServletRequest request; 
	
	@Autowired  
	protected  HttpServletResponse response; 
	/**
	 * @Description: 将字符串以json格式输出
	 * @Author Yang Cheng
	 * @Date: Feb 9, 2012 1:53:02 AM
	 * @param jsonStr
	 * @throws IOException
	 * @return void
	 */
	public void printStr(String jsonStr) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/x-json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(jsonStr);
		pw.flush();
		pw.close();
	}
	
	/** 
	* @Description: 将对象以json格式输出
	* @Author Yang Cheng
	* @Date: Feb 9, 2012 1:53:58 AM  
	* @param obj
	* @throws Exception  
	* @return void    
	 * @throws IOException 
	*/ 
	public void printObject(Object obj) throws IOException {
		JSONObject jsObject = JSONObject.fromObject(obj);
		printStr(jsObject.toString());
	}
	
	/** 
	* @Description: 将字集合以json格式输出
	* @Author Yang Cheng 
	* @Date: Feb 9, 2012 1:54:24 AM  
	* @param list
	* @throws Exception  
	* @return void    
	*/ 
	public void printArray(List<?> list) throws IOException{
		JSONArray jsArray = JSONArray.fromObject(list);
		printStr(jsArray.toString());
	}
	
}
