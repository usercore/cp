package com.qbb.jdbc.datasource;

public class DynamicDateSourceHolder {

	private static final String MASTER = "master";
	private static final String SLAVE = "slave";
	private static final String READONLY = "readOnly";
	private static final String DEFAULT = "default";

	private static final ThreadLocal<String> dataSource = new ThreadLocal<String>();

	private static void setDataSource(String dataSourceKey) {
		dataSource.set(dataSourceKey);
	}

	private static String getDataSource() {
		return dataSource.get();
	}

	public static boolean isMasterDataSource() {
		return getDataSource() == MASTER;
	}

	public static boolean isSlaveDataSource() {
		return getDataSource() == SLAVE;
	}

	public static boolean isReadOnlyDataSource() {
		return getDataSource() == READONLY;
	}

	public static boolean isDefaultDataSource() {
		return getDataSource() == null || getDataSource() == DEFAULT;
	}

	public static void useMasterDataSource() {
		setDataSource(MASTER);
	}

	public static void useSlaveDataSource() {
		setDataSource(SLAVE);
	}

	public static void useReadOnlyDataSource() {
		setDataSource(READONLY);
	}

	public static void useDefaultDataSource() {
		setDataSource(DEFAULT);
	}

	public static void cleanup() {
		dataSource.remove();
	}

}