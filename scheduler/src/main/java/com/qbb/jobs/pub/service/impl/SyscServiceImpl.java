package com.qbb.jobs.pub.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbb.jobs.pub.dao.SysSendMessageDao;
import com.qbb.jobs.pub.model.SysSendMessage;
import com.qbb.jobs.pub.service.ISyscService;
import com.qbb.util.DateUtil;
import com.qbb.util.client.QbbServiceRPC;

@Service
public class SyscServiceImpl implements ISyscService {

	Logger logger = Logger.getLogger(SyscServiceImpl.class);

	@Autowired
	private SysSendMessageDao sysSendMessageDao;

	public void setSysSendMessageDao(SysSendMessageDao sysSendMessageDao) {
		this.sysSendMessageDao = sysSendMessageDao;
	}

	// 发送短信服务-实时发送
	public void sendMessage(String sendEmaiphone, String sendText) {
		logger.info("---------开始插入短信...发送手机号为：" + sendEmaiphone + ",短信内容为" + sendText + " ---------");
		String[] phone = sendEmaiphone.split(",");
		String actina = "1039";
		for (int i = 0; i < phone.length; i++) {
			if (phone[i].length() != 11) {
				continue;
			}
			Map<String, Object> inMap = new HashMap<String, Object>();
			inMap.put("phone", phone[i]);
			inMap.put("content", sendText);
			Map<String, Object> returnMap = null;
			try {
				returnMap = QbbServiceRPC.getData(inMap, actina);
				logger.info("发送短信的结果为："+returnMap);
				//记录短信日志
				logger.info(DateUtil.dateToString(new Date())+"|"+sendEmaiphone+"|"+sendText+"|"+returnMap.get("erorcd").toString()+"|"+returnMap.get("errmsg").toString()+"|scheduler|");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("发送短信报文异常");
			}
		}
	}

	// 控制发信息的频率
	public long sendMessage(String sendEmaiphone, String sendText, long time) {
		logger.info("开始判断是否发送短信...");
		long now = new Date().getTime();
		// 30分钟内不重复发送
		if (now > time + 1800000) {
			sendMessage(sendEmaiphone, sendText);
			return now;
		}
		logger.info("短信频率发送过快...");
		return time;
	}

	@Override
	public int addMessage(String send_type,String send_emaiphone, String send_text, String status, String msg_code, String msg_type) {
		int row = 0;
		String[] phone = send_emaiphone.split(",");
		for (int i = 0; i < phone.length; i++) {
			SysSendMessage ssm = new SysSendMessage();
			ssm.setSendUuid(UUID.randomUUID().toString());
			ssm.setSendType(send_type);
			ssm.setMsgType(msg_type);
			ssm.setSendCode("-1");
			ssm.setSendText(send_text);
			ssm.setStatus(status);
			ssm.setSendEmaiphone(phone[i]);
			ssm.setSendTime(new Date());
			ssm.setCreateTime(new Date());
			ssm.setChannel("WEB");
			ssm.setMsgCode(msg_code);
			ssm.setSendUserid("scheduler");
			ssm.setTimingState("0");
			row += sysSendMessageDao.insert(ssm);
		}
		logger.info("插入表中的短信号码为: " + send_emaiphone + ", 内容为: " + send_text + ",状态为: " + status + ", 营销类型为: " + msg_code + ", 短信类型为: " + msg_type);
		return row;
	}
}
