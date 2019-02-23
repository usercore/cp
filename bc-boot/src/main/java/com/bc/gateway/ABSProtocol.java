package com.bc.gateway;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.bc.gateway.reqPacket.Packet;
import com.bc.util.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 *
 */
public abstract class ABSProtocol {
    private static final Logger log = LoggerFactory.getLogger(ABSProtocol.class);

    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected DecimalFormat df = new DecimalFormat("0.00");

    public String invoke(Packet packet) throws BusinessException {
        //解析请求数据,验证数据合法性
        decodeReqInfo(packet);
        //调用协议处理过程
        process();
        return encodeRes();
    }


    /**
     * @return void    返回类型
     * @throws
     * @Description: 解析报文
     */
    protected abstract void decodeReqInfo(Packet packet) throws BusinessException;

    /**
     * @return void    返回类型
     * @throws
     * @Description: 协议处理过程
     */
    protected abstract void process() throws BusinessException;

    /**
     * @return String    返回类型
     * @throws
     * @Description: 组装返回字符
     */
    protected abstract String encodeRes() throws BusinessException;

    protected String convertJson(Map<String, String> map, Map<String, List<Object>> objectMap, Map<String, String[]> propertys) {
        StringBuffer sb = new StringBuffer("{\"erorcd\":\"000000\",\"errmsg\":\"\",");
        for (String key : map.keySet()) {
            sb.append("\"" + key + "\":" + map.get(key) + ",");
        }
        for (String key : objectMap.keySet()) {
            sb.append("\"" + key + "\":[");
            for (Object obj : objectMap.get(key)) {
                try {
                    sb.append(JSON.json(obj, propertys.get(key)) + ",");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sb.lastIndexOf(",") == sb.length() - 1) {
                sb.deleteCharAt(sb.length() - 1).append("]");
            } else {
                sb.append("]");
            }
        }
        return sb.append("}").toString();
    }

    protected String getJsonValue(JSONObject jsonObject, String key) {
        Object o = jsonObject.get(key);
        if (o == null) {
            return "";
        } else {
            return o.toString();
        }
    }
}
