package com.bc.sys.query;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.data.service.IBaseService;

@Service("sysQueryService")
public class SysQueryServiceImpl implements ISysQueryService{

	static Logger log = Logger.getLogger(SysQueryServiceImpl.class);
	
	@Autowired
	IBaseService baseService;
	
	@Override
	public List<Map<String,Object>> listSysQueryByActina(String actina) {
		actina = actina.trim();
		String sql = " select query_sql,list_name from interface_res_list   where actina_id = ?";
		Object[] params = {actina};
		
		List<Map<String,Object>> sysQueryList = baseService.getObjList(sql, params);
		log.info(sysQueryList);
		
		return sysQueryList;
	}
}
