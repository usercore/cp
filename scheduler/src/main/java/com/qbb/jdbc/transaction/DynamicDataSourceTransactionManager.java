package com.qbb.jdbc.transaction;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import com.qbb.jdbc.datasource.DynamicDateSourceHolder;

/**
 * 动态数据源事务管理器
 * 
 *
 */
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {

	// 是否动态切换数据源。
	// 使用spring声明式事务作为依据，如事务为只读，则使用只读数据源，默认采用主数据源
	private boolean switchDynamicDataSource = true;

	private static final long serialVersionUID = -4729807897717605488L;

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		if (this.switchDynamicDataSource && DynamicDateSourceHolder.isDefaultDataSource()) {
			if (definition.isReadOnly()) {
				DynamicDateSourceHolder.useReadOnlyDataSource();
			} else {
				DynamicDateSourceHolder.useMasterDataSource();
			}
		}
		super.doBegin(transaction, definition);
	}

	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		DynamicDateSourceHolder.cleanup();
	}

	public boolean isSwitchDynamicDataSource() {
		return switchDynamicDataSource;
	}

	public void setSwitchDynamicDataSource(boolean switchDynamicDataSource) {
		this.switchDynamicDataSource = switchDynamicDataSource;
	}

}