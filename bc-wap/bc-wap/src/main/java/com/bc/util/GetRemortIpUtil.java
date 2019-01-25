package com.bc.util;

import javax.servlet.http.HttpServletRequest;

public class GetRemortIpUtil {
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		System.out.println(ip+"*------");
		String ipAdd = "";
		if(ip.indexOf(",") == -1){
			if("0:0:0:0:0:0:0:1".equals(ip.trim())){
				ipAdd = "127.0.0.1";
			}else{
				ipAdd = ip.trim();
			}
		}else{
			String [] iparray = ip.split(",");
			ipAdd = iparray[0].trim();
		}
		return ipAdd;
	}
}
