package com.qbb.util;



import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Result extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = -737962224540454905L;

	public Result(Map<String, Object> args) {
		super(args);
	}

	public Result() {
		super();
	}

	public String getString(String key) {
		String value = null;
		if (get(key) != null) {
			value = (String) get(key);
		}
		return value;
	}

	public Integer getInt(String key) {
		Integer value = null;
		if (get(key) != null) {
			value = (Integer) get(key);
		}
		return value;
	}

	public BigDecimal getBigDecimal(String key) {
		BigDecimal value = null;
		if (get(key) != null) {
			value = (BigDecimal) get(key);
		}
		return value;
	}

	public Long getLong(String key) {
		Long value = null;
		if (get(key) != null) {
			value = (Long) get(key);
		}
		return value;
	}

	public Short getShort(String key) {
		Short value = null;
		if (get(key) != null) {
			value = (Short) get(key);
		}
		return value;
	}

	public Double getDouble(String key) {
		Double value = null;
		if (get(key) != null) {
			value = (Double) get(key);
		}
		return value;
	}

	public Float getFloat(String key) {
		Float value = null;
		if (get(key) != null) {
			value = (Float) get(key);
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(String key) {
		T value = null;
		if (get(key) != null) {
			value = (T) get(key);
		}
		return value;
	}

	public boolean isBlank(String key) {
		return this.get(key) == null;
	}

	public boolean isEmpey(String key) {
		return this.get(key) == null || "".equals(this.getString(key));
	}
}
