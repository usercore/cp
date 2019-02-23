
package com.bc.filter.scheme.service;


import com.bc.filter.scheme.domain.FilterScheme;
import com.bc.util.db.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.bc.filter.scheme.dao.FilterSchemeMapper;
import com.bc.util.db.IBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.bc.util.exception.BusinessException;
import java.util.List;
/**
 * 
 */
 @Service("filterSchemeService")
public class FilterSchemeServiceImpl extends BaseServiceImpl<FilterScheme> implements IFilterSchemeService{

	@Autowired
	FilterSchemeMapper filterSchemeMapper;
	
	@Override
	protected IBaseMapper<FilterScheme> getBaseMapper() {
		return filterSchemeMapper;
	}
}
