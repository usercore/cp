package com.qbb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class PostUrl {
	public static String getURLContent(String urlStr) {

		// 请求的url
		URL url = null;

  	    // 请求的输入流
		BufferedReader in = null;

		// 输入流的缓冲
		StringBuffer sb = new StringBuffer();

		try {
			url = new URL(urlStr);

			in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			String str = null;

			// 一行一行进行读入
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
		} catch (Exception ex) {

		} finally {
			try {
				if (in != null) {
					in.close(); // 关闭流
				}
			} catch (IOException ex) {

			}
		}
		String result = sb.toString();
		return result;
	}
	public static void main(String[] args) {
		System.out.println(PostUrl.getURLContent("http://wx.dzzst.com/lottery/periodInfo/gsk3.json"));
	}
}
