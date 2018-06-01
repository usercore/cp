package com.qbb.jobs.pub.enums;

public enum OrgAccount {

	QBB_DS("000", "深圳市钱爸爸电子商务有限公司"),
	PA_SXF("PA_SXF", "平安存管手续费子帐户"),
	PA_WC("PA_WC", "平安存管尾差子账户"),
	PA_ZYZZ("PA_ZYZZ", "平安存管自有资金子帐户"),
	PA_LX("PA_LX", "平安存管利息子帐户");

	public String ID;
	public String NAME;

	OrgAccount(String ID, String NAME) {
		this.ID = ID;
		this.NAME = NAME;
	}
	
	
}
