package com.qbb.util.db;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;



public interface IBaseMapper<T extends Serializable> {
	/*****************CRUD操作********************/
	public void insert(T t);
	
	public int updateByPk(T t);
	public int updateByExample(@Param("record") T record, @Param("example") T example);
	
	public T selectByPk(@Param("pk") String pk);
	public int deleteByPk(@Param("pk") String pk);
	
	public Integer countByExample(T t);
	public List<T> selectByExample(T t);
}
