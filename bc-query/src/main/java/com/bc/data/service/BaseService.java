package com.bc.data.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bc.data.dao.BaseDao;
import com.bc.data.util.DBUtil;

/**
 * @author lzk
 */
@Service("baseService")
public class BaseService implements IBaseService {
	@Autowired
	protected BaseDao baseDao;

	/**
	 * 根据 map key 与 value 分页查询数据
	 * @param param
	 * @param pageNumber 页数
	 * @param pageSize 每页条数
	 * @param orderBy  排序规则
	 * @return
	 */
	public List<Map<String, Object>> query(Map<String, String> param, String pageNumber, String pageSize, String orderBy) {
		List<Object> paraList = new ArrayList<Object>();
		param.put("pageNumber", pageNumber);
		param.put("pageSize", pageSize);
		param.put("orderBy", orderBy);
		String sql = initQuerySql(param, paraList);
		List<Map<String, Object>> olist = baseDao.getObjList(sql, paraList.toArray());
		return olist;
	}

	/**
	 * 根据 map key 与 value 查询数据
	 * @param param
	 * @param orderBy 排序规则
	 * @return
	 */
	public List<Map<String, Object>> query(Map<String, String> param, String orderBy) {
		List<Object> paraList = new ArrayList<Object>();
		param.put("orderBy", orderBy);
		String sql = initQuerySql(param, paraList);
		List<Map<String, Object>> olist = baseDao.getObjList(sql, paraList.toArray());
		return olist;
	}

	/**
	 * 分页查总数
	 * 
	 * @param param
	 * @return
	 */
	public int queryCount(Map<String, String> param) {
		List<Object> paraList = new ArrayList<Object>();
		String sql = initQueryCountSql(param, paraList);
		int count = baseDao.getCount(sql, paraList.toArray());
		return count;
	}


	public List<Map<String, Object>> query(String sql, Map<String, String> param) {
		Map<String, String> wh = new HashMap<String, String>();
		List<Object> paramList = new ArrayList<Object>();
		DBUtil.transferParam4Query(param, wh, "a",paramList);
		String where = (String) wh.get("where");

		if (!where.equals("")){
			sql += where ;
		}
		return baseDao.getObjList(sql, paramList.toArray());
	}


	public String saveObject(Map<String, Object> param, String userName) {
		String table = (String) param.get("tableName");

		String id = (String) param.get("id");
		Map<String, String> wh = new HashMap<String, String>();

		if (id == null || id.equals("")) {
			List<Object> list = DBUtil.transferParam4Insert(param, wh, userName);
			list.add(1);
			Object[] params = list.toArray();
			String columns = wh.get("columns");
			String holders = wh.get("holders");
			String sql = "INSERT INTO " + table;
			sql += "("+ columns + ") values (" + holders + ")";
			baseDao.editObject(sql, params);
			String uid = (String) params[0];
			return uid;
		} else {
			Object[] params = DBUtil.transferParam4Update(param, wh, userName);
			String columns = wh.get("set");
			String sql = "UPDATE " + table + " SET ";
			sql += columns + " where id ='" + id + "'";
			baseDao.editObject(sql, params);
			return id;
		}
	}

	public List<Map<String, Object>> getObjList(String sql,Object[] objs) {
		return baseDao.getObjList(sql, objs);
	}
	public int getCount(String sql, Object[] params) {
		return baseDao.getCount(sql, params);
	}

	/**
	 * 批量更新与插入操作
	 * @param sql
	 * @param columns
	 * @param params
	 */
	public void batchOperate(String sql, List<String> columns, List<Map<String,String>> params) {
		baseDao.batchUpdate(sql, columns, params);
	}


	public int update(String sql, Object[] params) {
		return baseDao.update(sql, params);
	}
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void importList(String sql, List<String> column, List<Map<String,String>> vals) {
		// sql可能会超长,每次查询300个
		int count = 300;
		int times = 0;
		int total = vals.size();

		while (count * times < vals.size()) {
			int begin = count * times;
			int end = count * (times + 1);
			if (end > total)
				end = total;
			List li = vals.subList(begin, end);
			batchOperate(sql, column, li);

			times++;
		}
	}

	private String initQuerySql(Map<String, String> param, List<Object> paraList) {

		String splitPage = "";
		if (param.get("pageNumber") != null && !param.get("pageNumber").equals("")) {
			int page = Integer.parseInt(param.get("pageNumber"));
			int offset = (page - 1) * Integer.parseInt(param.get("pageSize"));
			splitPage += " limit " + offset + ", " + param.get("pageSize");
		}

		String tableName = (String) param.get("tableName");
		String sql = "select * from " + tableName + " as t ";

		Map<String, String> wh = new HashMap<String, String>();
		DBUtil.transferParam4Query(param, wh, "t",paraList);

		String where = (String) wh.get("where");
		if (where != null && !where.equals("")) {
			sql += "where " + where;
		}

		String orderBy = param.get("orderBy");
		if (orderBy != null && !orderBy.equals("")) {
			sql += " order by " + orderBy;
		}
		sql += splitPage;
		return sql;
	}

	private String initQueryCountSql(Map<String, String> param, List<Object> paraList) {
		String tableName = (String) param.get("tableName");
		String sql = "select count(*) from " + tableName + " as t ";
		List<Object> paramList = new ArrayList<Object>();
		Map<String, String> wh = new HashMap<String, String>();
		DBUtil.transferParam4Query(param, wh, "t",paramList);

		String where = (String) wh.get("where");
		if (where != null && !where.equals("")) {
			sql += "where " + where;
		}
		return sql;
	}
}
