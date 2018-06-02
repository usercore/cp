package com.qbb.util.db;

import java.io.Serializable;
import java.util.List;

import com.qbb.util.exception.MijiaBusinessException;


public abstract class BaseServiceImpl <T extends Serializable> implements IBaseService<T>{

	public void insert(T t) throws MijiaBusinessException{
		getBaseMapper().insert(t);
	}
	public int deleteByPk(String pk) throws MijiaBusinessException{
		return getBaseMapper().deleteByPk(pk);
	}
	public int updateByPk(T t) throws MijiaBusinessException{
		return getBaseMapper().updateByPk(t);
	}
	public T selectByPk(String pk) throws MijiaBusinessException{
		return getBaseMapper().selectByPk(pk);
	}
	public Integer countByExample(T t)throws MijiaBusinessException{
		return getBaseMapper().countByExample(t);
	}
	public List<T> selectByExample(T t)throws MijiaBusinessException{
		return getBaseMapper().selectByExample(t);
	}
	
	public int updateByExample(T record, T example) throws MijiaBusinessException {
		return getBaseMapper().updateByExample(record, example);
	}
	protected abstract IBaseMapper<T> getBaseMapper();
}
