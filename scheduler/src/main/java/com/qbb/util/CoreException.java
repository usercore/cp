package com.qbb.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by chen on 2017/2/15.
 */
public class CoreException {
    private static Log log = LogFactory.getLog(CoreException.class);

    //    邮件发送人集合
    //    邮件发送人集合
    private static List<String> tomail = new ArrayList<String>();
    private static List<String> ccmail = new ArrayList<String>();

    static {
        tomail.add("usercore@163.com");
    }

    /**
     * 异常处理
     * 邮件操作
     *
     * @param e
     */
    public static void error(Exception e) {
        StringBuffer buffer = new StringBuffer(1024);
        getErrorStr(e, buffer);
        String content = buffer.toString();

        log.error(content);
        sendMail(content);

        e.printStackTrace();
    }

    public static void sendMail(String content) {
        try {
            String to = StringUtils.join(tomail.iterator(), ",");
            String cc = StringUtils.join(ccmail.iterator(), ",");

//            EmailEntity entity = new EmailEntity("999", to, "scheduler异常");
//            entity.setCc(cc);

            String datestr = DateUtil.dateToString(new Date());
            content = MethodUtil.converToStr(content).concat("====执行时间:[" + datestr + "]");

//            entity.setContent(content);
//            MessageHelper.OTHER().sendEmail(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getErrorStr(Exception e, StringBuffer buf) {
        StackTraceElement[] stack = e.getStackTrace();
        buf.append(e.getClass().getCanonicalName()).append(": ")
                .append(e.getMessage());
        for (int i = 0; i < stack.length; i++) {
            StackTraceElement ste = stack[i];
            buf.append("\r\n\tat ").append(ste.getClassName()).append(".")
                    .append(ste.getMethodName()).append("(")
                    .append(ste.getFileName()).append(":")
                    .append(ste.getLineNumber()).append(")");
        }
    }

    public static void error(Map<String, Object> resultMap) {
        if (resultMap == null) {
            resultMap = new HashMap<String, Object>();
        }
        String erorcd = MethodUtil.converToStr(resultMap.get("erorcd"));
        if ("000000".equals(erorcd)) {
            return;
        }
        String errmsg = MethodUtil.converToStr(resultMap.get("errmsg"));
        String content = "scheduler异常!{erorcd:" + erorcd + "},{errmsg:" + errmsg + "}";

        log.error(content);

        sendMail(content);
    }
}
