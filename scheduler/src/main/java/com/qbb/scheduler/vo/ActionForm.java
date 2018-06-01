package com.qbb.scheduler.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ActionForm extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = -737962224540454905L;

	public ActionForm(Map<String, Object> args) {
		super(args);
	}

	public ActionForm() {
		super();
	}

	public String getString(String key) {
		String value = null;
		if (get(key) != null) {
			if (isArrays(key)) {
				String[] values = getArrays(key);
				if (values.length > 1) {
					throw new ClassCastException(
							"java.lang.String[] cannot be cast to java.lang.String");
				}
				value = values[0];
			} else {
				value = (String) get(key);
			}
		}
		return value;
	}

	public Integer getInt(String key) {
		return Integer.parseInt(getString(key));
	}

	public Long getLong(String key) {
		return Long.parseLong(getString(key));
	}

	public Short getShort(String key) {
		return Short.parseShort(getString(key));
	}

	public Double getDouble(String key) {
		return Double.parseDouble(getString(key));
	}

	public Float getFloat(String key) {
		return Float.parseFloat(getString(key));
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(String key) {
		return (T)this.get(key);
	}

	public String[] getArrays(String key) {
		if (get(key) != null) {
			if (isArrays(key)) {
				return (String[]) super.get(key);
			} else {
				return new String[] { (String) get(key) };
			}
		} else {
			return null;
		}
	}

	public List<Object> getList(String key) {
		String[] array = getArrays(key);
		List<Object> result = null;
		if(array != null) {
			result = new ArrayList<Object>();
			for (int i = 0; i < array.length; i++) {
				result.add(array[i]);
			}
		}
		return result;
	}

	@Override
	public Object get(Object key) {
		Object value = null;
		if (isArrays(key)) {
			String[] values = (String[]) super.get(key);
			if (values != null) {
				value = values.length > 1 ? values : values[0];
			}
		} else {
			value = super.get(key);
		}
		return value;
	}

	public boolean isArrays(Object key) {
		return super.get(key) instanceof String[];
	}

	public boolean isEmpty(Object key) {
		return super.get(key) == null || super.get(key).equals("");
	}
}
