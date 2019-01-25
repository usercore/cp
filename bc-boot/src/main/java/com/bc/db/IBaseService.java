package com.bc.db;

import java.io.Serializable;
import java.util.List;

import com.bc.util.exception.BusinessException;

public interface IBaseService<T extends Serializable> {
	
	public void insert(T t) throws BusinessException ;
	
	public int deleteByPk(String pk) throws BusinessException ;
	
	public int updateByPk(T t) throws BusinessException ;
	
	public int updateByExample(T record,T example)throws BusinessException;
	
	public T selectByPk(String pk) throws BusinessException ;
	
	public Integer countByExample(T t)throws BusinessException;
	
	public List<T> selectByExample(T t)throws BusinessException;
}
