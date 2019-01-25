package com.bc.data.util;

/**
 * @author lzk
 */
class ArrayUtils {
	public static boolean contains(Object[] objs, Object obj) {
		for (Object o : objs) {
			if (o.equals(obj)) {
				return true;
			}
		}
		return false;
	}
}
