package com.bc.sys.query;

import com.bc.data.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysQueryService")
public class SysQueryServiceImpl implements ISysQueryService {
    private static final Logger log = LoggerFactory.getLogger(SysQueryServiceImpl.class);


    @Autowired
    IBaseService baseService;

    @Override
    public List<Map<String, Object>> listSysQueryByActina(String actina) {
        actina = actina.trim();
        String sql = " select query_sql,list_name from interface_res_list   where actina_id = ?";
        Object[] params = {actina};

        List<Map<String, Object>> sysQueryList = baseService.getObjList(sql, params);
        log.info(sysQueryList.toString());

        return sysQueryList;
    }
}
