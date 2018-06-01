package com.qbb.jdbc.datasource;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 可动态切换的数据源
 * <p>
 * 可配置一个主库，多个从库和多个只读库，采用负载均衡加权轮询算法来动态切换
 * </p>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	protected transient Log logger = LogFactory.getLog(getClass());
	// 动态数据源开关
	private boolean switchDynamicDataSource = true;
	// 主数据源
	private DataSource masterDataSource;
	// 从数据源
	private List<DataSource> slaveDataSource;
	// 只读数据源
	private List<DataSource> readOnlyDataSource;
	// 多个从数据源的负载权重，默认权重为1
	private String slaveDataSourceWeight;
	// 多个只读数据源的负载权重，默认权重为1
	private String readOnlyDataSourceWeight;
	private static WeightedRoundRobin slaveWeightedRoundRobin;
	private static WeightedRoundRobin readOnlyWeightedRoundRobin;

	@Override
	public void afterPropertiesSet() {
		if (masterDataSource == null) {
			throw new IllegalArgumentException("Property 'masterDataSource' is required");
		}
		if (slaveDataSource != null && slaveDataSource.size() > 1) {
			if (slaveDataSourceWeight == null || slaveDataSourceWeight.trim().isEmpty()) {
				int[] weight = new int[slaveDataSource.size()];
				for (int i = 0; i < weight.length; i++) {
					weight[i] = 1;
				}
			} else {
				slaveDataSourceWeight = slaveDataSourceWeight.trim();
				String[] temp = slaveDataSourceWeight.split(":");
				if (temp.length != slaveDataSource.size()) {
					throw new IllegalArgumentException("Property 'slaveDataSourceWeight' value set error, The correct format is 'x:x:x'");
				}
				int[] weight = new int[slaveDataSource.size()];
				for (int i = 0; i < weight.length; i++) {
					weight[i] = Integer.parseInt(temp[i]);
				}
				slaveWeightedRoundRobin = new WeightedRoundRobin(weight);
			}
		}

		if (readOnlyDataSource != null && readOnlyDataSource.size() > 1) {
			if (readOnlyDataSourceWeight == null || readOnlyDataSourceWeight.trim().isEmpty()) {
				int[] weight = new int[readOnlyDataSource.size()];
				for (int i = 0; i < weight.length; i++) {
					weight[i] = 1;
				}
			} else {
				readOnlyDataSourceWeight = readOnlyDataSourceWeight.trim();
				String[] temp = readOnlyDataSourceWeight.split(":");
				if (temp.length != readOnlyDataSource.size()) {
					throw new IllegalArgumentException("Property 'readOnlyDataSourceWeight' value set error, The correct format is 'x:x:x'");
				}
				int[] weight = new int[readOnlyDataSource.size()];
				for (int i = 0; i < weight.length; i++) {
					weight[i] = Integer.parseInt(temp[i]);
				}
				readOnlyWeightedRoundRobin = new WeightedRoundRobin(weight);
			}
		}

	}

	/**
	 * 根据标识获取数据源
	 * 
	 * @return
	 */
	@Override
	protected DataSource determineTargetDataSource() {
		DataSource dataSource = null;
		if (!switchDynamicDataSource) {
			return masterDataSource;
		}
		if (DynamicDateSourceHolder.isMasterDataSource()) {
			dataSource = masterDataSource;
			logger.debug("Using Master DataSource");
		} else if (DynamicDateSourceHolder.isReadOnlyDataSource()) {
			if (readOnlyDataSource == null) {
				dataSource = masterDataSource;
				logger.error("'readOnlyDataSource' uninitialized, use the default dataSource  'masterDataSource'");
			} else {
				dataSource = readOnlyDataSource.get(readOnlyDataSource.size() == 1 ? 0 : readOnlyWeightedRoundRobin.next());
				logger.debug("Using ReadOnly DataSource");
			}
		} else if (DynamicDateSourceHolder.isSlaveDataSource()) {
			if (slaveDataSource == null) {
				dataSource = masterDataSource;
				logger.error("'slaveDataSource' uninitialized, use the default dataSource  'masterDataSource'");
			} else {
				dataSource = slaveDataSource.get(slaveDataSource.size() == 1 ? 0 : slaveWeightedRoundRobin.next());
				logger.debug("Using Slave DataSource");
			}
		} else {
			dataSource = masterDataSource;
		}
		return dataSource;
	}

	/**
	 * 负载均衡加权轮询算法
	 * 
	 *
	 */
	private static class WeightedRoundRobin {
		public int[] weight;
		public int cw = 0;
		public int number = -1;// 当前SERVER的序号,开始是-1
		public int max;// 最大权重
		public int gcd;// 最大公约数

		public WeightedRoundRobin(int[] weight) {
			this.init(weight);
		}

		private void init(int[] weight) {
			this.weight = weight;
			this.max = getMaxWeight(weight);
			this.gcd = gcd(weight);
		}

		/**
		 * 求最大公约数
		 * 
		 * @param array
		 * @return
		 */
		public int gcd(int[] ary) {
			int min = ary[0];
			for (int i = 0; i < ary.length; i++) {
				if (ary[i] < min) {
					min = ary[i];
				}
			}
			while (min >= 1) {
				boolean isCommon = true;
				for (int i = 0; i < ary.length; i++) {
					if (ary[i] % min != 0) {
						isCommon = false;
						break;
					}
				}
				if (isCommon) {
					break;
				}
				min--;
			}
			return min;
		}

		/**
		 * 求最大值，权重
		 * 
		 * @return
		 */

		public int getMaxWeight(int[] ary) {
			int max = 0;
			for (int i = 0; i < ary.length; i++) {
				if (max < ary[i]) {
					max = ary[i];
				}
			}
			return max;
		}

		/**
		 * 获取下一个序号
		 * 
		 * @return
		 */
		public Integer next() {
			while (true) {
				number = (number + 1) % weight.length;
				if (number == 0) {
					// cw比较因子，从最大权重开始，以最大公约数为步长递减
					cw = cw - gcd;
					if (cw <= 0) {
						cw = max;
						if (cw == 0)
							return null;
					}
				}
				if (weight[number] >= cw)
					return number;
			}
		}

	}

	public DataSource getMasterDataSource() {
		return masterDataSource;
	}

	public void setMasterDataSource(DataSource masterDataSource) {
		this.masterDataSource = masterDataSource;
	}

	public List<DataSource> getReadOnlyDataSource() {
		return readOnlyDataSource;
	}

	public void setReadOnlyDataSource(List<DataSource> readOnlyDataSource) {
		this.readOnlyDataSource = readOnlyDataSource;
	}

	public List<DataSource> getSlaveDataSource() {
		return slaveDataSource;
	}

	public void setSlaveDataSource(List<DataSource> slaveDataSource) {
		this.slaveDataSource = slaveDataSource;
	}

	public boolean isSwitchDynamicDataSource() {
		return switchDynamicDataSource;
	}

	public void setSwitchDynamicDataSource(boolean switchDynamicDataSource) {
		this.switchDynamicDataSource = switchDynamicDataSource;
	}

	public String getSlaveDataSourceWeight() {
		return slaveDataSourceWeight;
	}

	public void setSlaveDataSourceWeight(String slaveDataSourceWeight) {
		this.slaveDataSourceWeight = slaveDataSourceWeight;
	}

	public String getReadOnlyDataSourceWeight() {
		return readOnlyDataSourceWeight;
	}

	public void setReadOnlyDataSourceWeight(String readOnlyDataSourceWeight) {
		this.readOnlyDataSourceWeight = readOnlyDataSourceWeight;
	}

}