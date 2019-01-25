package com.bc.data.util;

import org.springframework.core.NestedRuntimeException;

/**
 * @author lzk
 */
@SuppressWarnings("serial")
public class DaoException extends NestedRuntimeException {

	public DaoException(String msg) {
		super(msg);
	}
	
	public DaoException(String msg,Throwable obj) {
		super(msg,obj);
	}
}
