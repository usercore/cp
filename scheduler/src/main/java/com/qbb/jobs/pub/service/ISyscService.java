package com.qbb.jobs.pub.service;


public interface ISyscService {
	
	/**
	 * @param sendEmaiphone
	 * @param sendText
	 * @author mrrc
	 */
	public void sendMessage(String sendEmaiphone,String sendText);
	
	/**
	 * 控制发信息的频率
	 * @param sendEmaiphone
	 * @param sendText
	 * @param time 任务所在类需定义一静态变量
	 * @return
	 * @author mrrc
	 */
	public long sendMessage(String sendEmaiphone, String sendText ,long time);
	
	/**
	 * @description 短信插表
	 * @author mrrc
	 * @date 2017年3月21日 下午5:42:15
	 * @param send_emaiphone 手机号
	 * @param send_text 短信内容
	 * @param status 状态 0 待发送 1 已发送 2 发送失败 3 黑名单
	 * @param msg_code 0 非营销 1 营销
	 * @param msg_type 短信类型
	 * @return
	 */
	public int addMessage(String send_type,String send_emaiphone, String send_text,String status,String msg_code,String msg_type);
}
