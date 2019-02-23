package com.bc.data.service;

import java.util.List;
import java.util.Map;

public interface IBaseService {

	/**
	 * 鏍规嵁 map key 涓� value 鍒嗛〉鏌ヨ鏁版嵁
	 * @param param
	 * @param pageNumber 椤垫暟
	 * @param pageSize 姣忛〉鏉℃暟
	 * @param orderBy  鎺掑簭瑙勫垯
	 * @return
	 */
	List<Map<String, Object>> query(Map<String, String> param, String pageNumber, String pageSize, String orderBy);

	/**
	 * 鏍规嵁 map key 涓� value 鏌ヨ鏁版嵁
	 * @param param
	 * @param orderBy 鎺掑簭瑙勫垯
	 * @return
	 */
	 List<Map<String, Object>> query(Map<String, String> param, String orderBy);

	/**
	 * 鍒嗛〉鏌ユ�绘暟
	 * 
	 * @param param
	 * @return
	 */
	 int queryCount(Map<String, String> param);

	 List<Map<String, Object>> query(String sql, Map<String, String> param);

	 String saveObject(Map<String, Object> param, String userName);

	 List<Map<String, Object>> getObjList(String sql, Object[] objs);

	 int getCount(String sql, Object[] params);

	/**
	 * 鎵归噺鏇存柊涓庢彃鍏ユ搷浣�
	 * @param sql
	 * @param columns
	 * @param params
	 */
	 void batchOperate(String sql, List<String> columns, List<Map<String, String>> params);

	 int update(String sql, Object[] params);

	 void importList(String sql, List<String> column, List<Map<String, String>> vals);

}