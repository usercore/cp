/*
 * Copyright 2014-2015 the original author hm.
 *
 */
package com.qbb.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p> The six logging levels used by <code>Log</code> are (in order):
 * <ol>
 * <li>trace (the least serious)</li>
 * <li>debug</li>
 * <li>info</li>
 * <li>warn</li>
 * <li>error</li>
 * <li>fatal (the most serious)</li>
 * </ol>
 * 
 * @author hm
 * 
 * create 2014-11-11 hm
 */
public class LogUtil {
	
//	@SuppressWarnings("unchecked")
//	private Class clazz = null;
	private Log logger = null;

	public LogUtil(@SuppressWarnings("rawtypes") Class clazz) {
//		this.clazz = clazz;
		logger = LogFactory.getLog(clazz);
	}
	
    /**
     * <p> Is debug logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than debug. </p>
     *
     * @return true if debug is enabled in the underlying logger.
     */
    public boolean isDebugEnabled() {
    	return logger.isDebugEnabled();
    }


    /**
     * <p> Is error logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than error. </p>
     *
     * @return true if error is enabled in the underlying logger.
     */
    public boolean isErrorEnabled() {
    	return logger.isErrorEnabled();
    }


    /**
     * <p> Is fatal logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than fatal. </p>
     *
     * @return true if fatal is enabled in the underlying logger.
     */
    public boolean isFatalEnabled() {
    	return logger.isFatalEnabled();
    }


    /**
     * <p> Is info logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than info. </p>
     *
     * @return true if info is enabled in the underlying logger.
     */
    public boolean isInfoEnabled() {
    	return logger.isInfoEnabled();
    }


    /**
     * <p> Is trace logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than trace. </p>
     *
     * @return true if trace is enabled in the underlying logger.
     */
    public boolean isTraceEnabled() {
    	return logger.isTraceEnabled();
    }


    /**
     * <p> Is warn logging currently enabled? </p>
     *
     * <p> Call this method to prevent having to perform expensive operations
     * (for example, <code>String</code> concatenation)
     * when the log level is more than warn. </p>
     *
     * @return true if warn is enabled in the underlying logger.
     */
    public boolean isWarnEnabled() {
    	return logger.isWarnEnabled();
    }


    // -------------------------------------------------------- Logging Methods


    /**
     * <p> Log a message with trace log level. </p>
     *
     * @param message log this message
     */
    public void trace(Object message) {
    	logger.trace(message);
    }


    /**
     * <p> Log an error with trace log level. </p>
     *
     * @param message log this message
     * @param t log this cause
     */
    public void trace(Object message, Throwable t) {
    	logger.trace(message, t);
    }


    /**
     * <p> Log a message with debug log level. </p>
     *
     * @param message log this message
     */
    public void debug(Object message) {
    	logger.debug(message);
    }


    /**
     * <p> Log an error with debug log level. </p>
     *
     * @param message log this message
     * @param t log this cause
     */
    public void debug(Object message, Throwable t) {
    	logger.debug(message, t);
    }


    /**
     * <p> Log a message with info log level. </p>
     *
     * @param message log this message
     */
    public void info(Object message) {
    	logger.info(message);
    }


    /**
     * <p> Log an error with info log level. </p>
     *
     * @param message log this message
     * @param t log this cause
     */
    public void info(Object message, Throwable t) {
    	logger.info(message, t);
    }


    /**
     * <p> Log a message with warn log level. </p>
     *
     * @param message log this message
     */
    public void warn(Object message) {
    	logger.warn(message);
    }


    /**
     * <p> Log an error with warn log level. </p>
     *
     * @param message log this message
     * @param t log this cause
     */
    public void warn(Object message, Throwable t) {
    	logger.warn(message, t);
    }


    /**
     * <p> Log a message with error log level. </p>
     *
     * @param message log this message
     */
    public void error(Object message) {
    	logger.error(message);
    }


    /**
     * <p> Log an error with error log level. </p>
     *
     * @param message log this message
     * @param t log this cause
     */
    public void error(Object message, Throwable t) {
    	logger.error(message, t);
    }


    /**
     * <p> Log a message with fatal log level. </p>
     *
     * @param message log this message
     */
    public void fatal(Object message) {
    	logger.fatal(message);
    }


    /**
     * <p> Log an error with fatal log level. </p>
     *
     * @param message log this message
     * @param t log this cause
     */
    public void fatal(Object message, Throwable t) {
    	logger.fatal(message, t);
    }


}
