package com.bc.util.db;

import com.bc.util.exception.BusinessException;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T extends Serializable> {
	
	void insert(T t) throws BusinessException ;
	
	int deleteByPk(String pk) throws BusinessException ;
	
	int updateByPk(T t) throws BusinessException ;
	
	int updateByExample(T record, T example)throws BusinessException;
	
	T selectByPk(String pk) throws BusinessException ;
	
	Integer countByExample(T t)throws BusinessException;
	
	List<T> selectByExample(T t)throws BusinessException;
	
	//T getUniqueData(List<T> list)throws BusinessException;
}
