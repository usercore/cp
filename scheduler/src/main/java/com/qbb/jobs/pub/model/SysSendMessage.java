package com.qbb.jobs.pub.model;

import java.util.Date;

public class SysSendMessage {
    /**
     * <pre>
     * 
     * 表字段 : sys_send_message.send_uuid
     * </pre>
     */
    private String sendUuid;

    /**
     * <pre>
     * 0  发送短信
            1 发送邮箱
     * 表字段 : sys_send_message.send_type
     * </pre>
     */
    private String sendType;

    /**
     * <pre>
     * 0 注册
            1 添加银行卡
            2 提现
            3 找回密码
            9 其他(不用校验短信)  11 体验币激活(app) 12 忘记交易密码(app) 13 流标
     * 表字段 : sys_send_message.msg_type
     * </pre>
     */
    private String msgType;

    /**
     * <pre>
     * 发送验证码
     * 表字段 : sys_send_message.send_code
     * </pre>
     */
    private String sendCode;

    /**
     * <pre>
     * 状态( 0  未发送  1 已发送)
     * 表字段 : sys_send_message.status
     * </pre>
     */
    private String status;

    /**
     * <pre>
     * 
     * 表字段 : sys_send_message.send_emaiphone
     * </pre>
     */
    private String sendEmaiphone;

    /**
     * <pre>
     * 
     * 表字段 : sys_send_message.send_time
     * </pre>
     */
    private Date sendTime;

    /**
     * <pre>
     * 
     * 表字段 : sys_send_message.create_time
     * </pre>
     */
    private Date createTime;

    /**
     * <pre>
     * 上次登录IP
     * 表字段 : sys_send_message.send_ip
     * </pre>
     */
    private String sendIp;

    /**
     * <pre>
     * 上次登录MAC地址
     * 表字段 : sys_send_message.send_mac
     * </pre>
     */
    private String sendMac;

    /**
     * <pre>
     * 用户来源渠道苹果:IPH/IPAD/IPHS 安卓：ADR/APAD  平台：WEB
     * 表字段 : sys_send_message.channel
     * </pre>
     */
    private String channel;

    /**
     * <pre>
     * 批次号
     * 表字段 : sys_send_message.group_id
     * </pre>
     */
    private String groupId;

    /**
     * <pre>
     * 模版号
     * 表字段 : sys_send_message.tl_id
     * </pre>
     */
    private String tlId;

    /**
     * <pre>
     * 0 梦网短信 1 玄武短信
     * 表字段 : sys_send_message.msg_code
     * </pre>
     */
    private String msgCode;

    /**
     * <pre>
     * 发送人userid(knp_user)
     * 表字段 : sys_send_message.send_userid
     * </pre>
     */
    private String sendUserid;

    /**
     * <pre>
     * 备注（记录短信发送失败原因）
     * 表字段 : sys_send_message.remark
     * </pre>
     */
    private String remark;

    /**
     * <pre>
     * 定时发送时间
     * 表字段 : sys_send_message.sending_time
     * </pre>
     */
    private Date sendingTime;

    /**
     * <pre>
     * 状态( 0  正常发送  1 定时发送)
     * 表字段 : sys_send_message.timing_state
     * </pre>
     */
    private String timingState;

    /**
     * <pre>
     * 审核备注（审核不通过的原因）
     * 表字段 : sys_send_message.check_remark
     * </pre>
     */
    private String checkRemark;

    /**
     * <pre>
     * 审核时间
     * 表字段 : sys_send_message.check_time
     * </pre>
     */
    private Date checkTime;

    /**
     * <pre>
     * 审核人
     * 表字段 : sys_send_message.check_userid
     * </pre>
     */
    private String checkUserid;

    /**
     * <pre>
     * 
     * 表字段 : sys_send_message.send_text
     * </pre>
     */
    private String sendText;

    /**
     * <pre>
     * 获取：
     * 表字段：sys_send_message.send_uuid
     * </pre>
     *
     * @return sys_send_message.send_uuid：
     */
    public String getSendUuid() {
        return sendUuid;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：sys_send_message.send_uuid
     * </pre>
     *
     * @param sendUuid
     *            sys_send_message.send_uuid：
     */
    public void setSendUuid(String sendUuid) {
        this.sendUuid = sendUuid;
    }

    /**
     * <pre>
     * 获取：0  发送短信
            1 发送邮箱
     * 表字段：sys_send_message.send_type
     * </pre>
     *
     * @return sys_send_message.send_type：0  发送短信
            1 发送邮箱
     */
    public String getSendType() {
        return sendType;
    }

    /**
     * <pre>
     * 设置：0  发送短信
            1 发送邮箱
     * 表字段：sys_send_message.send_type
     * </pre>
     *
     * @param sendType
     *            sys_send_message.send_type：0  发送短信
            1 发送邮箱
     */
    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    /**
     * <pre>
     * 获取：0 注册
            1 添加银行卡
            2 提现
            3 找回密码
            9 其他(不用校验短信)  11 体验币激活(app) 12 忘记交易密码(app) 13 流标
     * 表字段：sys_send_message.msg_type
     * </pre>
     *
     * @return sys_send_message.msg_type：0 注册
            1 添加银行卡
            2 提现
            3 找回密码
            9 其他(不用校验短信)  11 体验币激活(app) 12 忘记交易密码(app) 13 流标
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * <pre>
     * 设置：0 注册
            1 添加银行卡
            2 提现
            3 找回密码
            9 其他(不用校验短信)  11 体验币激活(app) 12 忘记交易密码(app) 13 流标
     * 表字段：sys_send_message.msg_type
     * </pre>
     *
     * @param msgType
     *            sys_send_message.msg_type：0 注册
            1 添加银行卡
            2 提现
            3 找回密码
            9 其他(不用校验短信)  11 体验币激活(app) 12 忘记交易密码(app) 13 流标
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * <pre>
     * 获取：发送验证码
     * 表字段：sys_send_message.send_code
     * </pre>
     *
     * @return sys_send_message.send_code：发送验证码
     */
    public String getSendCode() {
        return sendCode;
    }

    /**
     * <pre>
     * 设置：发送验证码
     * 表字段：sys_send_message.send_code
     * </pre>
     *
     * @param sendCode
     *            sys_send_message.send_code：发送验证码
     */
    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }

    /**
     * <pre>
     * 获取：状态( 0  未发送  1 已发送)
     * 表字段：sys_send_message.status
     * </pre>
     *
     * @return sys_send_message.status：状态( 0  未发送  1 已发送)
     */
    public String getStatus() {
        return status;
    }

    /**
     * <pre>
     * 设置：状态( 0  未发送  1 已发送)
     * 表字段：sys_send_message.status
     * </pre>
     *
     * @param status
     *            sys_send_message.status：状态( 0  未发送  1 已发送)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * <pre>
     * 获取：
     * 表字段：sys_send_message.send_emaiphone
     * </pre>
     *
     * @return sys_send_message.send_emaiphone：
     */
    public String getSendEmaiphone() {
        return sendEmaiphone;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：sys_send_message.send_emaiphone
     * </pre>
     *
     * @param sendEmaiphone
     *            sys_send_message.send_emaiphone：
     */
    public void setSendEmaiphone(String sendEmaiphone) {
        this.sendEmaiphone = sendEmaiphone;
    }

    /**
     * <pre>
     * 获取：
     * 表字段：sys_send_message.send_time
     * </pre>
     *
     * @return sys_send_message.send_time：
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：sys_send_message.send_time
     * </pre>
     *
     * @param sendTime
     *            sys_send_message.send_time：
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * <pre>
     * 获取：
     * 表字段：sys_send_message.create_time
     * </pre>
     *
     * @return sys_send_message.create_time：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：sys_send_message.create_time
     * </pre>
     *
     * @param createTime
     *            sys_send_message.create_time：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * <pre>
     * 获取：上次登录IP
     * 表字段：sys_send_message.send_ip
     * </pre>
     *
     * @return sys_send_message.send_ip：上次登录IP
     */
    public String getSendIp() {
        return sendIp;
    }

    /**
     * <pre>
     * 设置：上次登录IP
     * 表字段：sys_send_message.send_ip
     * </pre>
     *
     * @param sendIp
     *            sys_send_message.send_ip：上次登录IP
     */
    public void setSendIp(String sendIp) {
        this.sendIp = sendIp;
    }

    /**
     * <pre>
     * 获取：上次登录MAC地址
     * 表字段：sys_send_message.send_mac
     * </pre>
     *
     * @return sys_send_message.send_mac：上次登录MAC地址
     */
    public String getSendMac() {
        return sendMac;
    }

    /**
     * <pre>
     * 设置：上次登录MAC地址
     * 表字段：sys_send_message.send_mac
     * </pre>
     *
     * @param sendMac
     *            sys_send_message.send_mac：上次登录MAC地址
     */
    public void setSendMac(String sendMac) {
        this.sendMac = sendMac;
    }

    /**
     * <pre>
     * 获取：用户来源渠道苹果:IPH/IPAD/IPHS 安卓：ADR/APAD  平台：WEB
     * 表字段：sys_send_message.channel
     * </pre>
     *
     * @return sys_send_message.channel：用户来源渠道苹果:IPH/IPAD/IPHS 安卓：ADR/APAD  平台：WEB
     */
    public String getChannel() {
        return channel;
    }

    /**
     * <pre>
     * 设置：用户来源渠道苹果:IPH/IPAD/IPHS 安卓：ADR/APAD  平台：WEB
     * 表字段：sys_send_message.channel
     * </pre>
     *
     * @param channel
     *            sys_send_message.channel：用户来源渠道苹果:IPH/IPAD/IPHS 安卓：ADR/APAD  平台：WEB
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * <pre>
     * 获取：批次号
     * 表字段：sys_send_message.group_id
     * </pre>
     *
     * @return sys_send_message.group_id：批次号
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * <pre>
     * 设置：批次号
     * 表字段：sys_send_message.group_id
     * </pre>
     *
     * @param groupId
     *            sys_send_message.group_id：批次号
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * <pre>
     * 获取：模版号
     * 表字段：sys_send_message.tl_id
     * </pre>
     *
     * @return sys_send_message.tl_id：模版号
     */
    public String getTlId() {
        return tlId;
    }

    /**
     * <pre>
     * 设置：模版号
     * 表字段：sys_send_message.tl_id
     * </pre>
     *
     * @param tlId
     *            sys_send_message.tl_id：模版号
     */
    public void setTlId(String tlId) {
        this.tlId = tlId;
    }

    /**
     * <pre>
     * 获取：0 梦网短信 1 玄武短信
     * 表字段：sys_send_message.msg_code
     * </pre>
     *
     * @return sys_send_message.msg_code：0 梦网短信 1 玄武短信
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * <pre>
     * 设置：0 梦网短信 1 玄武短信
     * 表字段：sys_send_message.msg_code
     * </pre>
     *
     * @param msgCode
     *            sys_send_message.msg_code：0 梦网短信 1 玄武短信
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * <pre>
     * 获取：发送人userid(knp_user)
     * 表字段：sys_send_message.send_userid
     * </pre>
     *
     * @return sys_send_message.send_userid：发送人userid(knp_user)
     */
    public String getSendUserid() {
        return sendUserid;
    }

    /**
     * <pre>
     * 设置：发送人userid(knp_user)
     * 表字段：sys_send_message.send_userid
     * </pre>
     *
     * @param sendUserid
     *            sys_send_message.send_userid：发送人userid(knp_user)
     */
    public void setSendUserid(String sendUserid) {
        this.sendUserid = sendUserid;
    }

    /**
     * <pre>
     * 获取：备注（记录短信发送失败原因）
     * 表字段：sys_send_message.remark
     * </pre>
     *
     * @return sys_send_message.remark：备注（记录短信发送失败原因）
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <pre>
     * 设置：备注（记录短信发送失败原因）
     * 表字段：sys_send_message.remark
     * </pre>
     *
     * @param remark
     *            sys_send_message.remark：备注（记录短信发送失败原因）
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * <pre>
     * 获取：定时发送时间
     * 表字段：sys_send_message.sending_time
     * </pre>
     *
     * @return sys_send_message.sending_time：定时发送时间
     */
    public Date getSendingTime() {
        return sendingTime;
    }

    /**
     * <pre>
     * 设置：定时发送时间
     * 表字段：sys_send_message.sending_time
     * </pre>
     *
     * @param sendingTime
     *            sys_send_message.sending_time：定时发送时间
     */
    public void setSendingTime(Date sendingTime) {
        this.sendingTime = sendingTime;
    }

    /**
     * <pre>
     * 获取：状态( 0  正常发送  1 定时发送)
     * 表字段：sys_send_message.timing_state
     * </pre>
     *
     * @return sys_send_message.timing_state：状态( 0  正常发送  1 定时发送)
     */
    public String getTimingState() {
        return timingState;
    }

    /**
     * <pre>
     * 设置：状态( 0  正常发送  1 定时发送)
     * 表字段：sys_send_message.timing_state
     * </pre>
     *
     * @param timingState
     *            sys_send_message.timing_state：状态( 0  正常发送  1 定时发送)
     */
    public void setTimingState(String timingState) {
        this.timingState = timingState;
    }

    /**
     * <pre>
     * 获取：审核备注（审核不通过的原因）
     * 表字段：sys_send_message.check_remark
     * </pre>
     *
     * @return sys_send_message.check_remark：审核备注（审核不通过的原因）
     */
    public String getCheckRemark() {
        return checkRemark;
    }

    /**
     * <pre>
     * 设置：审核备注（审核不通过的原因）
     * 表字段：sys_send_message.check_remark
     * </pre>
     *
     * @param checkRemark
     *            sys_send_message.check_remark：审核备注（审核不通过的原因）
     */
    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    /**
     * <pre>
     * 获取：审核时间
     * 表字段：sys_send_message.check_time
     * </pre>
     *
     * @return sys_send_message.check_time：审核时间
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * <pre>
     * 设置：审核时间
     * 表字段：sys_send_message.check_time
     * </pre>
     *
     * @param checkTime
     *            sys_send_message.check_time：审核时间
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * <pre>
     * 获取：审核人
     * 表字段：sys_send_message.check_userid
     * </pre>
     *
     * @return sys_send_message.check_userid：审核人
     */
    public String getCheckUserid() {
        return checkUserid;
    }

    /**
     * <pre>
     * 设置：审核人
     * 表字段：sys_send_message.check_userid
     * </pre>
     *
     * @param checkUserid
     *            sys_send_message.check_userid：审核人
     */
    public void setCheckUserid(String checkUserid) {
        this.checkUserid = checkUserid;
    }

    /**
     * <pre>
     * 获取：
     * 表字段：sys_send_message.send_text
     * </pre>
     *
     * @return sys_send_message.send_text：
     */
    public String getSendText() {
        return sendText;
    }

    /**
     * <pre>
     * 设置：
     * 表字段：sys_send_message.send_text
     * </pre>
     *
     * @param sendText
     *            sys_send_message.send_text：
     */
    public void setSendText(String sendText) {
        this.sendText = sendText;
    }
}