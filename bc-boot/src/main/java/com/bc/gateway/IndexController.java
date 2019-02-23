package com.bc.gateway;

import com.bc.boot.SpringUtil;
import com.bc.gateway.reqPacket.Packet;
import com.bc.util.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Packet packet = new Packet();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        OutputStream out = response.getOutputStream();
        String actina = "";

        try {

            paraseReq(request, packet);

            actina = packet.getActina();

            ABSProtocol protocol = (ABSProtocol) SpringUtil.getBean(actina.trim());

            String returnJSON = protocol.invoke(packet);

            log.info("return:" + returnJSON);

            byte[] result = packet.responsePacket(actina, returnJSON);
            out.write(result);

        } catch (BusinessException e) {
            e.printStackTrace();
            String returnJSON = "{\"erorcd\":\"" + e.getErrno() + "\",\"errmsg\":\"" + e.getMessage() + "\"}";
            log.info("return:" + returnJSON);
            byte[] result = packet.responsePacket(actina, returnJSON);
            out.write(result);
        } catch (Exception e) {
            log.error("exception:", e);
            String returnJSON = "{\"erorcd\":\"0000001\",\"errmsg\":\"服务器忙\"}";
            log.info("return:" + returnJSON);
            byte[] result = packet.responsePacket(actina, returnJSON);
            out.write(result);
        } finally {
            try {
                out.flush();
                out.close();
                response.flushBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/commonQuery")
    public void commonQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Packet packet = new Packet();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        OutputStream out = response.getOutputStream();
        String processBeen = "commonQuery";

        try {

            paraseReq(request, packet);

            ABSProtocol protocol = (ABSProtocol) SpringUtil.getBean(processBeen);

            String returnJSON = protocol.invoke(packet);

            log.info("return:" + returnJSON);

            byte[] result = packet.responsePacket(processBeen, returnJSON);
            out.write(result);

        } catch (BusinessException e) {
            e.printStackTrace();
            String returnJSON = "{\"erorcd\":\"" + e.getErrno() + "\",\"errmsg\":\"" + e.getMessage() + "\"}";
            log.info("return:" + returnJSON);
            byte[] result = packet.responsePacket(processBeen, returnJSON);
            out.write(result);
        } catch (Exception e) {
            log.error("exception:", e);
            String returnJSON = "{\"erorcd\":\"0000001\",\"errmsg\":\"服务器忙\"}";
            log.info("return:" + returnJSON);
            byte[] result = packet.responsePacket(processBeen, returnJSON);
            out.write(result);
        } finally {
            try {
                out.flush();
                out.close();
                response.flushBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void paraseReq(HttpServletRequest request, Packet packet) throws IOException {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        int len = request.getContentLength();

        byte b[] = new byte[len];
        b = request.getParameter("param").getBytes();
        bytearrayoutputstream.write(b);
        byte data[] = bytearrayoutputstream.toByteArray();
        packet.parseData(data);

        log.info(request.getRemoteAddr() + ":actina=" + packet.getActina());
        log.info(request.getRemoteAddr() + ":body=" + packet.getBody());
    }

}
