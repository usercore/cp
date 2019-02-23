package com.bc.gateway.impl;

import com.bc.protocol.util.CommunicationConfigNew;
import com.bc.protocol.util.ProtocolUtil;
import com.bc.protocol.util.XmlConfigUtil;
import com.bc.util.exception.BusinessException;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

public abstract class AbsCommProtocol {

    protected static final Logger log = LoggerFactory.getLogger(AbsCommProtocol.class);

    public String invoke(HttpServletRequest request, String xmlPath, String serviceCode) throws BusinessException {
        // 验证数据合法性，与解析数据
        CommunicationConfigNew config = XmlConfigUtil.getPrcsConfig(request.getParameter("actina"), xmlPath);
        Map<String, Object> paraMap = validateData(request, config);
        paraMap.put("termno", request.getParameter("termno"));
        paraMap.put("channel", request.getParameter("channel"));
        paraMap.put("app_version", request.getParameter("app_version"));
        paraMap.put("actina", request.getParameter("actina"));
        // 调用协议处理过程
        return process(paraMap, serviceCode);
    }

    /**
     * @return void 返回类型
     * @throws
     * @Description: 协议处理过程
     */
    protected abstract String process(Map<String, Object> paraMap, String serviceCode) throws BusinessException;

    /**
     * 验证数据合法性
     *
     * @param request
     * @throws BusinessException
     */
    private Map<String, Object> validateData(HttpServletRequest request, CommunicationConfigNew config) throws BusinessException {
        return ProtocolUtil.verifyRequest(config, request);
    }

    //protected abstract String getReqUrl();

    public JsonConfig convertJsonToString() {
        JsonConfig jsonConfig = new JsonConfig();
        JsonValueProcessor jp = new JsonValueProcessor() {

            @Override
            public Object processArrayValue(Object data, JsonConfig jsonConfig) {
                return data.toString();
            }

            @Override
            public Object processObjectValue(String propertyName, Object data, JsonConfig jsonConfig) {
                return data.toString();
            }

        };
        jsonConfig.registerJsonValueProcessor(BigDecimal.class, jp);
        jsonConfig.registerJsonValueProcessor(Integer.class, jp);
        jsonConfig.registerJsonValueProcessor(Long.class, jp);
        return jsonConfig;
    }
}
