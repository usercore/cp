package com.bc.protocol.util;

import com.bc.util.exception.BusinessException;
import org.dom4j.io.SAXReader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 * Description:
 *
 * @version 创建时间：2014-8-7 上午10:20:17
 * @author: hm
 * @Company: qianbaba
 * @E-mail: hanmin@qian88.com.cn
 */
public class XmlConfigUtil {
    private static final Logger log = LoggerFactory.getLogger(XmlConfigUtil.class);

    /**
     * 根据处理码取得交易配置信息
     *
     * @param prcscd 交易处理码（对应去除扩展名的文件名）
     * @return String
     */
    /*通读配置文件，所有的配置都放在该文件中，配置参照其中的示例*/
    private static Document commCfgDoc = null;


    private static Document procCfgDoc = null;

    private static Document beanCfgDoc = null;

    static {
//        //加载配置文件：APP客户端发送 和 返回 的数据
//        commCfgDoc = getConfigDocument("communication.xml");
//        //加载配置文件：调用存储过程输入 和输出参数
//        procCfgDoc = getConfigDocument("pkg_proc_mysql.xml");
    }

    public static CommunicationConfig getPrcsConfig(String actina) throws BusinessException {
        Element prcsCfgObj;
        try {
            prcsCfgObj = (Element) XPath.selectSingleNode(commCfgDoc, "/communication/packet[@actina='" + actina + "']");
            if (null == prcsCfgObj) {
                throw new BusinessException("交易接口不存在" + actina + ",请检查配置文件 communication.xml");
            }
            // 返回交易配置对象
            return new CommunicationConfig((Element) prcsCfgObj);
        } catch (JDOMException e) {
            throw new BusinessException("系统错误" + actina + ",请检查配置文件 communication.xml");
        }
    }

    public static CommunicationConfigNew getPrcsConfig(String actina, String xmlName) throws BusinessException {
        Element prcsCfgObj;
        try {
            Document xml = getProcCfgByStream(xmlName);
            prcsCfgObj = (Element) XPath.selectSingleNode(xml, "/communication/packet[@actina='" + actina + "']");
            if (null == prcsCfgObj) {
                return null;
            }
            // 返回交易配置对象
            return new CommunicationConfigNew((Element) prcsCfgObj);
        } catch (JDOMException e) {
            throw new BusinessException("系统错误" + actina + ",请检查配置文件 communication.xml");
        }
    }

    public static CommunicationIndexConfig getPrcsIndex(String actina, String xmlName) throws BusinessException {
        Element prcsCfgObj;
        try {
            Document xml = getProcCfgByStream(xmlName);
            prcsCfgObj = (Element) XPath.selectSingleNode(xml, "/communication/packet[@actina='" + actina + "']");
            if (null == prcsCfgObj) {
                return null;
            }
            // 返回交易配置对象
            return new CommunicationIndexConfig((Element) prcsCfgObj);
        } catch (JDOMException e) {
            throw new BusinessException("系统错误" + actina + ",请检查配置文件 " + xmlName);
        }
    }

    /**
     * 打成jar后获取的地址file:/home/ubuntu/bc-service/bc-service.jar!/BOOT-INF/classes!/communication_index.xml
     * 改成直接获取InputStream
     *
     * @param configXml
     * @return
     */

    public static Document getProcCfgByStream(String configXml) {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = null;
        try {
            // 默认为UTF-8格式
            InputStream in = XmlConfigUtil.class.getClassLoader().getResource(configXml).openStream();

//            InputStream in = XmlConfigUtil.class.getClassLoader().getResourceAsStream(configXml);
            Reader read = new InputStreamReader(in, "UTF-8");
            document = saxBuilder.build(read);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("doc2XmlFile error: ", e);
        }
        return document;

    }
/*	public static ProcConfig getProcConfig(String procna) throws Exception {
		log.info("存储过程名称:"+procna);
		Element prcsCfgObj=(Element)XPath.selectSingleNode(procCfgDoc, "/PkgProcMysql/packet[@procna='"+procna+"']");

		if(null==prcsCfgObj){
			throw new BusinessException("存储过程名不存在"+procna+",请检查配置文件pkg_proc_mysql.xml");
		}else {
			// 返回交易配置对象
			return new ProcConfig((Element) prcsCfgObj);
		}
	}*/


    /*获取配置文件的路径*/
    private static String getConfigFilePath(String configXml) {
//		String configPath = AppFunction.serverRootDirectory();
//        configPath += "WEB-INF/classes/"+configXml;
        URL url = XmlConfigUtil.class.getResource("/");
        String configPath = url.getPath();
        configPath += configXml;

//		String configPath = "D:\\qianbabawork\\qbb\\qian88_app\\src\\"+configXml;

        return configPath;
    }

    /*获取配置文件的XML Document对象*/
    private static Document getConfigDocument(String configXml) {

        String configFile = getConfigFilePath(configXml);
        log.info("d5------------------------------------------------:");

        File file = new File(configFile);
        log.info("d6------------------------------------------------:");
        /*将映射配置文件生成XML Document*/
        Document xmlDoc = JDomUtil.getDocument(file);
        log.info("d7------------------------------------------------:");
        return xmlDoc;
    }
}
