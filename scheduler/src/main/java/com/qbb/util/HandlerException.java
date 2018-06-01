package com.qbb.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 获取异常类相关信息
 * 
 * @author chenhuaiao
 * @version V1.0
 * @createTime 2014年5月14日 上午9:34:49
 */
public class HandlerException {
	private static Log lg = LogFactory.getLog(HandlerException.class);

	private HandlerException() {
	}

	/**
	 * 记录平台的异常日志信息
	 * 
	 * @param e
	 */
	public static void error(Exception e) {
		StringBuffer buf = new StringBuffer();
		try {
			StackTraceElement[] stack = e.getStackTrace();
			buf.append(e.getClass().getCanonicalName()).append(": ")
					.append(e.getMessage());
			for (int i = 0; i < stack.length; i++) {
				StackTraceElement ste = stack[i];
				// if((ste.getClassName().startsWith("com.qbb") ||
				// ste.getClassName().startsWith("qbb.crm")) &&
				// !ste.getClassName().equals(HandlerException.class.getCanonicalName())){
				buf.append("\r\n\tat ").append(ste.getClassName()).append(".")
						.append(ste.getMethodName()).append("(")
						.append(ste.getFileName()).append(":")
						.append(ste.getLineNumber()).append(")");
				// }
			}
		} catch (Exception e2) {
			buf.append(e2.getMessage());
		}

		lg.error(buf.toString());
	}
}
