package com.bc.gateway.query;

import com.alibaba.fastjson.JSON;
import com.bc.data.service.IBaseService;
import com.bc.gateway.ABSProtocol;
import com.bc.gateway.reqPacket.Packet;
import com.bc.sql.parase.SQLParser;
import com.bc.sql.parase.domain.BcBoundSql;
import com.bc.sys.query.ISysQueryService;
import com.bc.util.exception.BusinessException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用查询接口
 *
 * @author lizhenkui
 */
@Service("commonQuery")
@Scope("prototype")
public class CommonQuery extends ABSProtocol {

    static Logger log = Logger.getLogger(CommonQuery.class);

    @Autowired
    private IBaseService service;
    
    @Autowired
    ISysQueryService sysQueryService;

    private Map<String, Object> reqMap;
    String actina;
    String actinaId;
    Map<String, Object> errorMsgMap = new HashMap<String, Object>();

    @SuppressWarnings("unchecked")
    @Override
    protected void decodeReqInfo(Packet packet) throws BusinessException {

        reqMap = JSON.parseObject(packet.getBody(), Map.class);
        actina = packet.getActina();

    }

    @Override
    protected void process() {
        try {

            List<Map<String, Object>> commonSqlList = sysQueryService.listSysQueryByActina(actina);
            log.info("query------" + commonSqlList.size()) ;
            
            if(null != reqMap && null != reqMap.get("pageNo") && null != reqMap.get("pageSize") && !reqMap.get("pageNo").equals("") && !reqMap.get("pageSize").equals("")){
            	 reqMap.put("startRecord", (Integer.parseInt(reqMap.get("pageNo").toString())-1)*Integer.parseInt(reqMap.get("pageSize").toString()));
            	 reqMap.put("pageSize", Integer.parseInt(reqMap.get("pageSize").toString()));
            }
            for (Map<String, Object> map : commonSqlList) {
                SQLParser sqlParser = new SQLParser(map.get("query_sql").toString());
                log.info(map.get("query_sql"));
                BcBoundSql bcBoundSql = sqlParser.getBoundSql(reqMap);
                log.info(bcBoundSql.getSql());
                List<Map<String, Object>> resultList = service.getObjList(bcBoundSql.getSql(), bcBoundSql.getParams());
                errorMsgMap.put(map.get("list_name").toString(), resultList);
                log.info(resultList);

            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }


    @Override
    protected String encodeRes() throws BusinessException {
        String result = "";
        errorMsgMap.put("erorcd", "000000");
        errorMsgMap.put("errmsg", "查询成功");
        result = JSON.toJSONString(errorMsgMap).toString();

        return result;
    }

}
