package com.bc.data.dao.impl;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bc.data.dao.BaseDao;
import com.bc.data.util.DBUtil;


/**
 * @author lzk
 */
@SuppressWarnings("unchecked")
@Repository
public class BaseDaoImpl implements BaseDao {

	@Autowired
	private DBUtil util;

	@Override
	public void batchOperate(String sql, List<String> columns, List<?> objs) {
		util.batchOperate(sql, columns, objs);
	}
	public void batchUpdate(String sql,List<String> columns, List<Map<String,String>> objLs){
		util.batchUpdate(sql, columns, objLs);
	}
	public int update(String sql, Object[] obj){
		return util.update(sql, obj);
	}
	@Override
	public int editObject(String sql, Object[] obj) {
		return util.editObject(sql, obj);
	}

	public int getCount(String sql, Object[] obj){
		return util.getCount(sql, obj);
	}
	
	@Override
	public Map<String, ?> find(String sql, Object[] objs) {
		return util.getMap(sql, objs);
	}

	@Override
	public List<Map<String,Object>> getObjList(String sql, Object[] objs) {
		return util.getObjList(sql, objs);
	}

	
	@Override
	public int isExist(String sql, Object[] obj) {
		return util.isExist(sql, obj);
	}

	@Override
	public <T extends Serializable> void saveOrUpdateObject(String sql, T entry) {
		util.addOrUpdate(sql, entry);
	}

	@Override
	public <T extends Serializable> T getObject(String sql,Class<T> clazz,Object[] objs) {
		return (T)util.getObject(sql, clazz, objs);
	}
}
