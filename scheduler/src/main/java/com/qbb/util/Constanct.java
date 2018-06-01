package com.qbb.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 作者 : 陈怀傲
 * @version 创建时间：2015年6月26日 上午11:58:51 类说明
 */
public class Constanct {
    //    平台服务调用URL链接
    public static String SERVICE_URL = null;

    static {
        InputStream in = null;
        try {
            Properties prop = new Properties();
            in = Constanct.class.getResourceAsStream("/constanct.properties");
            prop.load(in);
            SERVICE_URL = (String) prop.get("service_url");
        } catch (IOException e) {
            HandlerException.error(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    HandlerException.error(e);
                }
            }
        }
    }
}
