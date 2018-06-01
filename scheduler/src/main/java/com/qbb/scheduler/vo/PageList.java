package com.qbb.scheduler.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表分页工具类，最终转化为json数据格式： { "success" : true, "code" : 0, "message" : "",
 * "total" : 0, "rows" : [] }
 * 
 */
public class PageList extends AbstractVO {

	private static final long serialVersionUID = -5726065211579111961L;

	// 总记录数
	private int totalCount = 0;
	// 结果集
	private List<?> rows = new ArrayList<>();

	/**
	 * 创建一个新的PageList
	 * 
	 * @param total
	 *            总记录数
	 * @param rows
	 *            结果集
	 * @return PageList
	 */
	public static PageList build(int total, List<?> rows) {
		return new PageList(total, rows);
	}

	public static PageList build(String message, int total, List<?> rows) {
		return new PageList(message, total, rows);
	}

	public static PageList build(int code, int total, List<?> rows) {
		return new PageList(code, total, rows);
	}

	public static PageList build(int code, String message, int total,
			List<?> rows) {
		return new PageList(code, message, total, rows);
	}

	public static PageList build() {
		PageList pageLsit = new PageList();
		return pageLsit;
	}

	// setter
	public PageList set(int total, List<?> rows) {
		this.setTotalCount(total);
		this.setRows(rows);
		return this;
	}

	public PageList set(String message) {
		this.setMessage(message);
		return this;
	}

	public PageList set(int code, String message) {
		this.setCode(code);
		this.setMessage(message);
		return this;
	}

	public PageList set(String message, int total, List<?> rows) {
		this.setMessage(message);
		this.setTotalCount(total);
		this.setRows(rows);
		return this;
	}

	public PageList set(int code, int total, List<?> rows) {
		this.setCode(code);
		this.setTotalCount(total);
		this.setRows(rows);
		return this;
	}

	public PageList set(int code, String message, int total, List<?> rows) {
		this.setCode(code);
		this.setMessage(message);
		this.setTotalCount(total);
		this.setRows(rows);
		return this;
	}

	// constructor
	private PageList(int total, List<?> rows) {
		this.totalCount = total;
		this.rows = rows;
	}

	private PageList(String message, int total, List<?> rows) {
		this.message = message;
		this.totalCount = total;
		this.rows = rows;
	}

	private PageList(int code, int total, List<?> rows) {
		this.code = code;
		this.totalCount = total;
		this.rows = rows;
	}

	private PageList(int code, String message, int total, List<?> rows) {
		this.code = code;
		this.message = message;
		this.totalCount = total;
		this.rows = rows;
	}

	private PageList() {
	}

	// end

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<?> getRows() {
		return rows;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getResultList() {
		return (List<T>)rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public boolean isSuccess() {
		return true;
	}

	@Override
	public String toString() {
		return "PageList [totalCount=" + totalCount + ", rows=" + rows
				+ ", success=" + success + ", code=" + code + ", message="
				+ message + "]";
	}

}
