package com.bc.db;

import java.io.Serializable;
import java.util.List;

import com.bc.util.exception.BusinessException;


public abstract class BaseServiceImpl <T extends Serializable> implements IBaseService<T>{

	public void insert(T t) throws BusinessException{
		getBaseMapper().insert(t);
	}
	public int deleteByPk(String pk) throws BusinessException{
		return getBaseMapper().deleteByPk(pk);
	}
	public int updateByPk(T t) throws BusinessException{
		return getBaseMapper().updateByPk(t);
	}
	public T selectByPk(String pk) throws BusinessException{
		return getBaseMapper().selectByPk(pk);
	}
	public Integer countByExample(T t)throws BusinessException{
		return getBaseMapper().countByExample(t);
	}
	public List<T> selectByExample(T t)throws BusinessException{
		return getBaseMapper().selectByExample(t);
	}
	
	public int updateByExample(T record, T example) throws BusinessException {
		return getBaseMapper().updateByExample(record, example);
	}
	protected abstract IBaseMapper<T> getBaseMapper();
}
