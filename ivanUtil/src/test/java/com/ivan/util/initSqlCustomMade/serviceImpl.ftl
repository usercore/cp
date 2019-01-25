
package com.qbb.${package}.service;


import com.qbb.${package}.domain.${fixName}DO;
import com.qbb.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.qbb.${package}.dao.${fixName}Mapper;
import com.qbb.util.db.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.qbb.util.exception.QbbBusinessException;
import java.util.List;
/**
 * 
 */
 @Service("${fixNameLower}Service")
public class ${fixName}ServiceImpl extends BaseServiceImpl<${fixName}DO> implements I${fixName}Service{

	@Autowired
	${fixName}Mapper ${fixNameLower}Mapper;
	
	@Override
	protected IBaseMapper<${fixName}DO> getBaseMapper() {
		return ${fixNameLower}Mapper;
	}
}
