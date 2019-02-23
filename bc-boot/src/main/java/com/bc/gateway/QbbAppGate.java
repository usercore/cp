package com.bc.gateway;

import com.bc.boot.SpringUtil;
import com.bc.common.constant.IPCConstans;
import com.bc.gateway.impl.AbsCommProtocol;
import com.bc.protocol.util.CommunicationIndexConfig;
import com.bc.protocol.util.XmlConfigUtil;
import com.bc.session.User;
import com.bc.util.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class QbbAppGate {

    @Autowired
    protected HttpServletRequest req;

    @Autowired
    protected HttpServletResponse res;
    private static final Logger log = LoggerFactory.getLogger(QbbAppGate.class);

    // 登陆用户交易ACTION
    @RequestMapping(value = "/bussInovk.action", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doJsonList() {
        //try {
        log.info("============请求App2.0开始================");
        String returnJSON = "";
        String app_version = req.getParameter("app_version");
        String actina = req.getParameter("actina");
        String channel = req.getParameter("channel");
        log.info(actina);
        try {
            CommunicationIndexConfig communicationIndexConfig = XmlConfigUtil.getPrcsIndex(req.getParameter("actina"), "communication_index.xml");
            if (communicationIndexConfig != null) {
                AbsCommProtocol protocol = (AbsCommProtocol) SpringUtil.getBean(communicationIndexConfig.getBeanName());// 默认been  commonProtocol
                log.info("获取index配置" + protocol);
                returnJSON = protocol.invoke(req, communicationIndexConfig.getXmlPath(), communicationIndexConfig.getServiceCode());
            } else {
                returnJSON = "{\"erorcd\":\"888888\",\"errmsg\":\"配置文件不存在，请检查调用接口是否正确\"}";
                return returnJSON;
            }

        } catch (BusinessException e) {// 捕获自定义异常
            returnJSON = "{\"erorcd\":\"" + e.getErrno() + "\",\"errmsg\":\"" + e.getMessage() + "\"}";
            return returnJSON;
        }

        log.info("returnJSONObj : " + returnJSON);

        // 转化为json格式返回给客户端
        log.info("返回数据：" + returnJSON.toString());
        log.info("============请求结束================");
        return returnJSON;
//		} catch (Exception e) {
//			log.error(e.getMessage(), e.fillInStackTrace());
//			return "";
//		}
    }


    protected HttpServletRequest request() {
        return req;
    }

    protected String request(String key) {
        return request().getParameter(key);
    }

    protected HttpSession session() {
        return req.getSession();
    }

    protected Object session(String key) {
        return session().getAttribute(key);
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

    public HttpServletResponse getRes() {
        return res;
    }

    public void setRes(HttpServletResponse res) {
        this.res = res;
    }

    protected String getUserName() {
        User user = (User) session().getAttribute(IPCConstans.SESSION_USER);
        String userName = "loginn";
        if (user != null) {
            userName = user.getUserName();
        }
        return userName;
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
