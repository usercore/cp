package com.qbb.util.db;

import java.io.Serializable;
import java.util.List;

import com.qbb.util.exception.MijiaBusinessException;

public interface IBaseService<T extends Serializable> {
	
	public void insert(T t) throws MijiaBusinessException ;
	
	public int deleteByPk(String pk) throws MijiaBusinessException ;
	
	public int updateByPk(T t) throws MijiaBusinessException ;
	
	public int updateByExample(T record,T example)throws MijiaBusinessException;
	
	public T selectByPk(String pk) throws MijiaBusinessException ;
	
	public Integer countByExample(T t)throws MijiaBusinessException;
	
	public List<T> selectByExample(T t)throws MijiaBusinessException;
}
