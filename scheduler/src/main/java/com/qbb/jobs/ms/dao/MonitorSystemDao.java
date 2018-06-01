package com.qbb.jobs.ms.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MonitorSystemDao {

	/**
	 * 检查当天未核保完成的标的
	 * 
	 * @return
	 */
	public int getNotJobcorecount();

	/**
	 * 检查当天财务审批未通过的数据
	 * 
	 * @return
	 */
	public int getNotRepcount();

	/**
	 * 检查当天系统未还款数据
	 * 
	 * @return
	 */
	public int getNotRepjobcount();

	/**
	 * 检查还款记录与资金流水流水匹配数据，还款后未给投资人款项的
	 * 
	 * @return
	 */

	public BigDecimal getNotFundMoney1();

	public BigDecimal getNotFundMoney2();

	public BigDecimal getNotFundMoney3();

	/**
	 * 正常还款资金匹配检查
	 * 
	 * @return
	 */
	public BigDecimal getNotFundBackMoney1();

	public BigDecimal getNotFundBackMoney2();

	public BigDecimal getNotFundBackMoney3();

	public BigDecimal getNotFundBackMoney4();

	/**
	 * 检查用户注册后缺少资金记录
	 * 
	 * @return
	 */
	public int getNotCnpUserFund();

	/**
	 * 检查电商体验币账户是否充足开始
	 * 
	 * @return
	 */

	public BigDecimal getSumExpMoney();

	public BigDecimal getOrgUsable();

	/**
	 * 判断每天的注册短信数量是否存在被机刷
	 * 
	 * @return
	 */
	public int getMsgCount();

	/**
	 * 有用户账户可用金额为负数
	 * 
	 * @return
	 */
	public int getUserUsableMinus();

	/**
	 * 查询移动端充值成功后添加的银行卡信息为空
	 * 
	 * @return
	 */
	public int getNotRepyayCount();

	/**
	 * 满标未核保的数据
	 * 
	 * @return
	 */
	public int getNotCore();

	/**
	 * 用户资金总额不等于可用+冻结+待收本金+待收利息
	 * 
	 * @return
	 */
	public int getWrongTotalCapital();

	/**
	 * 核保失败或者核保中的标的
	 * 
	 * @return
	 */
	public int getNotJobcorecountFalse();

	/**
	 * 微信支付失败
	 * 
	 * @return
	 */
	public int getWeChatPaymentFailed();

	/**
	 * 债权转让式核保异常
	 * 
	 * @return
	 */
	public int getNotCoreForCreditor();

	/**
	 * 还款异常
	 * 
	 * @return
	 */
	public int getNotJobrepcountFalse();

	/**
	 * 获取要发送的MO9自动投资成功列表
	 * 
	 * @return
	 */
    public List<Map<String, Object>> getAotoInvestSuccessList();

    /**
     * 查询短信黑名单 返回true 则是黑名单用户
     * 
     * @param userId
     * @param messageType
     * @return
     */
    public boolean queryIsBlackUser(Long userId, String messageType);
    /**
     * 获取需要发送汇款提醒的用户列表
     * 
     * @return
     */
    public List<Map<String, Object>> runMo9RepayMsg();
    
    /**
     * 获取需要发送汇款提醒的用户列表
     * 
     * @return
     */
    public List<Map<String, Object>> runMonthRepayMsg();
    
    
    /**
     * 定时任务运行监控
     * @return
     */
    public int getSchedulerStop();
    
    // public List<Map<String, Object>> getMo9AotoInvestSuccessList();
    //
    // public List<Map<String, Object>> getMo9AotoInvestSuccessList();

    public int getPaUnderwritingFailed();
    
    public int getPaRepaymentFail();
    
	/**
	 * 获取核保失败的记录
	 * 
	 * @return
	 */
	public int getUnderwritingFailed();
	
	/**
	 * 获取超过20分钟未开始核保的任务
	 * @return
	 */
	public int getUnderwritingNoStart();
	
	
	/**
	 * 检查超过90分钟还未发送短信的业务
	 * 
	 * @return
	 */
	public int getSysSendmessAge();
	
	
	/**
	 *  星期5查询周末核保且需要还款的标的数据
	 * @return
	 */
	public List<Map<String, Object>> queryReekendCoreBorrowAndRepayment();
	 
	
	public List<Map<String, Object>> getNoUnderwritingData();
	
	public List<Map<String, Object>> getNoRepaymentData();
	
	public int getPaBatchFail();
	
}
