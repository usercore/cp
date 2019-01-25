package com.bc.common.constant;

import java.util.Arrays;
import java.util.List;

public class IPCConstans {
	
	/**
	 * needToken_new_key  session key
	 */
	public static final String needToken_new_key = "need_token_new";
	/**
	 *  needToken_old_key  session key
	 */
	public static final String needToken_old_key = "need_token_old";
	/**
	 *  重复提交 true
	 */
	public static final String isNeedToken_true = "true";
	/**
	 *  入参名NeedToken 
	 */
	public static final String token_key = "NeedToken";
	
	/**
	 * 跳过重复提交严重的客户端类型
	 */
	public static final List<String> checkClientTypes = Arrays.asList("ADR","IPH","iPod","IPAD","simulator");
	
	/**
	 * check_last_version
	 */
	public static final String check_last_version = "3.2.11";
	
	/**
	 * session内容：user用户
	 */
	public static final String SESSION_USER = "user";

	
	public static boolean checkVersion(String cur_version) {
		return true;
	}

}
