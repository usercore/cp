
package com.bc.${package}.service;


import com.bc.${package}.domain.${fixName};
import com.bc.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.bc.${package}.dao.${fixName}Mapper;
import com.bc.util.db.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.bc.util.exception.BusinessException;
import java.util.List;
/**
 * 
 */
 @Service("${fixNameLower}Service")
public class ${fixName}ServiceImpl extends BaseServiceImpl<${fixName}> implements I${fixName}Service{

	@Autowired
	${fixName}Mapper ${fixNameLower}Mapper;
	
	@Override
	protected IBaseMapper<${fixName}> getBaseMapper() {
		return ${fixNameLower}Mapper;
	}
}
