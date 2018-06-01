/*
 * Copyright 2016-2099 the original author or authors.
 */
package com.qbb.jobs.pub.util;

import java.math.BigDecimal;

/**
 * 数学计算工具类
 * 
 * <pre>
 * 默认保留小数方式为四舍五入
 * </pre>
 */
public class MathCalculator {

	public static final int SCALE = 2;

	private BigDecimal value;
	private int roundingMode = BigDecimal.ROUND_HALF_UP;

	public static MathCalculator begin(Object value) {
		return new MathCalculator(value);
	}

	public MathCalculator(Object value) {
		this.value = conversion(value);
	}

	public MathCalculator setRoundingMode(int roundingMode) {
		this.roundingMode = roundingMode;
		return this;
	}

	public MathCalculator setScale(int newScale) {
		this.value = this.value.setScale(newScale, roundingMode);
		return this;
	}

	public Double diff(Object value, int scale) {
		subtract(value, scale);
		return this.value.abs().doubleValue();
	}

	public MathCalculator setScale(int newScale, int roundingMode) {
		this.value = this.value.setScale(newScale, roundingMode);
		return this;
	}

	public MathCalculator add(Object augend) {
		this.value = this.value.add(conversion(augend));
		return this;
	}

	public MathCalculator add(Object augend, int scale) {
		add(augend);
		setScale(scale, roundingMode);
		return this;
	}

	public MathCalculator subtract(Object subtrahend) {
		this.value = this.value.subtract(conversion(subtrahend));
		return this;
	}

	public MathCalculator subtract(Object subtrahend, int scale) {
		subtract(subtrahend);
		setScale(scale, roundingMode);
		return this;
	}

	public MathCalculator multiply(Object multiplicand) {
		this.value = this.value.multiply(conversion(multiplicand));
		return this;
	}

	public MathCalculator multiply(Object multiplicand, int scale) {
		multiply(multiplicand);
		setScale(scale, roundingMode);
		return this;
	}

	public MathCalculator divide(Object divisor) {
		this.value = this.value.divide(conversion(divisor));
		return this;
	}

	public MathCalculator divide(Object divisor, int scale) {
		this.value = this.value.divide(conversion(divisor), scale, roundingMode);
		return this;
	}

	public double doubleValue() {
		return this.value.doubleValue();
	}

	public float floatValue() {
		return this.value.floatValue();
	}

	public String stringValue() {
		return this.value.toPlainString();
	}

	public int intValue() {
		return this.value.intValue();
	}

	public long longValue() {
		return this.value.longValue();
	}

	public BigDecimal getValue() {
		return this.value;
	}

	private BigDecimal conversion(Object value) {
		BigDecimal temp = null;
		if (value == null) {
			throw new IllegalArgumentException("数值为空");
		}
		if (value instanceof BigDecimal) {
			temp = (BigDecimal) value;
		} else if (value instanceof String) {
			temp = new BigDecimal((String) value);
		} else if (value instanceof Long) {
			temp = new BigDecimal((Long) value);
		} else if (value instanceof Integer) {
			temp = new BigDecimal((Integer) value);
		} else if (value instanceof Double) {
			temp = new BigDecimal((Double) value);
		} else if (value instanceof Float) {
			temp = new BigDecimal((Float) value);
		} else if (value instanceof Character) {
			temp = new BigDecimal((Character) value);
		} else {
			throw new IllegalArgumentException("不支持的参数类型: " + value.getClass().getName());
		}
		return temp;
	}

}
